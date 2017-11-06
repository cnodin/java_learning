package cn.com.fubon.springboot.starter.jwt.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: binbin.wang
 * Date: 2017/10/28
 * Time: 15:43
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Component
public class TokenHelper {

    @Value("${app.name}")
    private String APP_NAME;

    @Value("${jwt.secret}")
    public String SECRET;

    @Value("${jwt.expires_in}")
    private int EXPIRES_IN;

    @Value("${jwt.mobile_expires_in}")
    private int MOBILE_EXPIRES_IN;

    @Value("${jwt.header}")
    private String AUTH_HEADER;

    @Value("${jwt.cookie}")
    private String AUTH_COOKIE;

    static final String AUDIENCE_UNKNOWN = "unknown";
    static final String AUDIENCE_WEB = "web";
    static final String AUDIENCE_MOBILE = "mobile";
    static final String AUDIENCE_TABLET = "tablet";

    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public Optional<String> getUsernameFromToken(String token){
        final Optional<Claims> claims = this.getAllClaimsFromToken(token);
        Optional<String> result = claims.map(Claims::getSubject);
        return result;
    }

    public Optional<Date> getIssuedAtDateFromToken(String token) {
        final Optional<Claims> claims = this.getAllClaimsFromToken(token);
        Optional<Date> result = claims.map(Claims::getIssuedAt);
        return result;
    }

    public Optional<String> getAudienceFromToken(String token) {
        final Optional<Claims> claims = this.getAllClaimsFromToken(token);
        Optional<String> result = claims.map(Claims::getAudience);
        return result;
    }

    public String generateToken(String username, Device device) {
        String audience = generateAudience(device);
        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(username)
                .setAudience(audience)
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate(device))
                .signWith( SIGNATURE_ALGORITHM, SECRET )
                .compact();
    }

    public Optional<String> refreshToken(String token, Device device) {
        final Optional<Claims> claims = this.getAllClaimsFromToken(token);
        if (!claims.isPresent()){
            return Optional.empty();
        }
        Claims theClaims = claims.get();
        theClaims.setIssuedAt(new Date());
        String refreshedToken = Jwts.builder()
                                    .setClaims(theClaims)
                                    .setExpiration(generateExpirationDate(device))
                                    .signWith( SIGNATURE_ALGORITHM, SECRET )
                                    .compact();
        Optional<String> result = Optional.of(refreshedToken);
        return result;
    }

    public Optional<String> getToken( HttpServletRequest request ) {
        /**
         *  Getting the token from Cookie store
         */
        Optional<Cookie> theAuthCookie = getCookieValueByName( request, AUTH_COOKIE );
        Optional<String> token = theAuthCookie.map(Cookie::getValue);

        /**
         *  Getting the token from Authentication header
         *  e.g Bearer your_token
         */
        Optional<String> theAuthHeader = Optional.ofNullable(request.getHeader(AUTH_HEADER));
        token = theAuthHeader.filter(value -> value.startsWith("Bearer "));
        return token;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {

        return false;
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    private Optional<Claims> getAllClaimsFromToken(String token){
        return Optional.ofNullable(Jwts.parser()
                                        .setSigningKey(SECRET)
                                        .parseClaimsJws(token).getBody());
    }

    private Date generateExpirationDate(Device device) {
        long theExpiresIn = device.isTablet() || device.isMobile() ? MOBILE_EXPIRES_IN : EXPIRES_IN;
        return new Date(new Date().getTime() + theExpiresIn * 1000);
    }

    private String generateAudience(Device device) {

        String audience = AUDIENCE_UNKNOWN;
        if (device.isNormal()) {
            audience = AUDIENCE_WEB;
        } else if (device.isTablet()) {
            audience = AUDIENCE_TABLET;
        } else if (device.isMobile()) {
            audience = AUDIENCE_MOBILE;
        }

        return audience;
    }

    private Optional<Cookie> getCookieValueByName(HttpServletRequest request, String name) {
        return Arrays.stream(request.getCookies()).filter(value ->
                value.getName().equals(name)).findFirst();
    }
}

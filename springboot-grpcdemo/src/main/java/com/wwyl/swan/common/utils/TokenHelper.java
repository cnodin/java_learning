package com.wwyl.swan.common.utils;

import com.wwyl.swan.common.provider.TimeProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: spockwang
 * Date: 11/20/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Component
public class TokenHelper {

    @Value("${spring.application.name}")
    private String APP_NAME;

    @Value("${jwt.secret}")
    public String SECRET;

    @Value("${jwt.expires_in}")
    private int EXPIRES_IN;

    @Value("${jwt.mobile_expires_in}")
    private int MOBILE_EXPIRES_IN;


    @Autowired
    TimeProvider timeProvider;

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    static final String AUDIENCE_UNKNOWN = "unknown";
    static final String AUDIENCE_WEB = "web";
    static final String AUDIENCE_MOBILE = "mobile";
    static final String AUDIENCE_TABLET = "tablet";

    private Date generateExpirationDate() {
        long expiresIn = EXPIRES_IN;
        return new Date(timeProvider.now().getTime() + expiresIn * 1000);
    }

    /**
     * 认证access token，默认3分钟有效
     * @param accessToken
     * @param subject
     * @return
     */
    public Boolean validateAccessToken(String accessToken, String subject) {
        final String username = getUsernameFromToken(accessToken);
        final Date created = getIssuedAtDateFromToken(accessToken);
        return (
                username != null &&
                        username.equals(subject) &&
                        isAccessTokenTimeValidate(created)
        );
    }

    public Date getIssuedAtDateFromToken(String token) {
        Date issueAt;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            issueAt = claims.getIssuedAt();
        } catch (Exception e) {
            issueAt = null;
        }
        return issueAt;
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public String generateToken(String username) {
        String audience = generateAudience();
        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(username)
                .setAudience(audience)
                .setIssuedAt(timeProvider.now())
                .setExpiration(generateExpirationDate())
                .signWith(SIGNATURE_ALGORITHM, SECRET)
                .compact();
    }

    public Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * accesstoken三分钟内有效
     * @param created
     * @return
     */
    private Boolean isAccessTokenTimeValidate(Date created) {
        return System.currentTimeMillis() <= DateUtils.addMinutes(created, 3).getTime();
    }

    private String generateAudience() {
        String audience = AUDIENCE_WEB;
        return audience;
    }
}
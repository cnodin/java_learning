package cn.com.fubon.springboot.starter.jwt.auth;

import cn.com.fubon.springboot.starter.jwt.config.JwtSecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: binbin.wang
 * Date: 2017/11/1
 * Time: 20:01
 * To change this template use File | Settings | File Templates.
 * Description:
 */

@Slf4j
@Service
public class JWTTokenProvider implements TokenProvider {

    @Value("application.name")
    private String applicationName;

    private JwtSecurityProperties jwtProperties;

    private long tokenValidityInSecondsForRemberMe;

    private long tokenValidityInSeconds;

    public JWTTokenProvider(JwtSecurityProperties jwtProperties){
        this.jwtProperties = jwtProperties;
    }

    static final String AUDIENCE_UNKNOWN = "unknown";
    static final String AUDIENCE_WEB = "web";
    static final String AUDIENCE_MOBILE = "mobile";
    static final String AUDIENCE_TABLET = "tablet";

    @PostConstruct
    public void init(){
        this.tokenValidityInSeconds =
                1000 * jwtProperties.getToken().getTokenValidityInSeconds();
        this.tokenValidityInSecondsForRemberMe =
                1000 * jwtProperties.getToken().getTokenValidityInSecondsForRememberMe();
    }

    @Override
    public String createToken(Authentication authentication, Boolean rememberMe) {
        Optional<Authentication> auth = Optional.ofNullable(authentication);

        String authorities = extractAuthorities(auth.map(Authentication::getAuthorities)
                            .orElseThrow(IllegalArgumentException::new));

        Date now = new Date(System.currentTimeMillis());
        Date validity = provideValidityDate(rememberMe);

        String subject = auth.map(Authentication::getName).orElseThrow(IllegalArgumentException::new);

//        String audience = generateAudience(device);

        JwtBuilder jwtBuilder = Jwts.builder()
                            .setHeaderParam("typ", "JWT")
                            .setIssuedAt(now)           //创建时间
                            .setIssuer(applicationName) //签发者
                            .setSubject(subject)        //主题，一般是个人信息
                            //.setAudience(generateAudience)
                            .setExpiration(validity)    //过期时间
                            .setNotBefore(now)          //系统时间之前的token都是不被承认的
                            .signWith(SignatureAlgorithm.HS256,
                                    jwtProperties.getToken().getSecret())
                            ;

        if (!StringUtils.isEmpty(authorities.trim())){
            //这里的claim是public claim，可以添加自己的字段
            jwtBuilder.claim(jwtProperties.getToken().getPayload().getAuthoritiesKey(), authorities);
        }
        return jwtBuilder.compact();
    }

    @Override
    public boolean validateToken(String token) {
        return Try.of(()->{
                Jwts.parser().setSigningKey(jwtProperties.getToken().getSecret()).parseClaimsJws(token);
                return true;
            }).recover(ex -> {
                log.info(String.format("Invalid JWT signature: %s", ex.getMessage()));
                return false;
            }).get();
    }

    @Override
    public Authentication getAuthentication(String token) {

        Claims claims = Jwts.parser().setSigningKey(jwtProperties.getToken().getSecret())
                        .parseClaimsJws(token).getBody();

        Collection<? extends GrantedAuthority> authorities =
                Try.of(() ->
                        Arrays.stream(jwtProperties.getToken().getPayload()
                                        .getAuthoritiesKey().toString().split(","))
                                        .map(SimpleGrantedAuthority::new)
                                        .collect(Collectors.toList())
                ).recover(ex -> Collections.emptyList()
                ).get();

        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    private String extractAuthorities(Collection<? extends GrantedAuthority> authorities){
        return authorities.stream().map(GrantedAuthority::getAuthority)
                                .collect(Collectors.joining(","));
    }

    private Date provideValidityDate(Boolean rememberMe){
        long now = System.currentTimeMillis();
        return rememberMe ? new Date(now + this.tokenValidityInSecondsForRemberMe)
                        : new Date(now + this.tokenValidityInSeconds);
    }

    private String generateAudience(Device device){
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
}

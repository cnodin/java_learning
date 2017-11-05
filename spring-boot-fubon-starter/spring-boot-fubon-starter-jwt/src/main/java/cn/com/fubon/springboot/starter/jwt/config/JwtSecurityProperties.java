package cn.com.fubon.springboot.starter.jwt.config;

import cn.com.fubon.springboot.starter.jwt.model.AuthenticationRequestBody;
import cn.com.fubon.springboot.starter.jwt.model.SimpleAuthenticationRequestBody;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: binbin.wang
 * Date: 2017/11/1
 * Time: 19:36
 * To change this template use File | Settings | File Templates.
 * Description: configuration properties for jwt security
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "fubon.config.jwt")
public class JwtSecurityProperties {

    private Token token = new Token();

    private Class<? extends AuthenticationRequestBody> authenticationRequestBody = SimpleAuthenticationRequestBody.class;

    private boolean enabled = true;

    private String url = "api/v1/login";

    @Getter
    @Setter
    public static class Token {
        private String tokenHeader = "Authorization";

        private String tokenSchema = "Bearer ";

        private String secret = UUID.randomUUID().toString();

        private long tokenValidityInSeconds = 1800;

        private long tokenValidityInSecondsForRememberMe = 2592000;

        private Payload payload = new Payload();

        @Getter
        @Setter
        public static class Payload {
            private String authoritiesKey = "auth";
            private String loginKey = "login";
        }
    }

}

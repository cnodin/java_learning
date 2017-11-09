package cn.com.fubon.springboot.starter.jwt.auth;

import cn.com.fubon.springboot.starter.jwt.config.JwtSecurityProperties;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created with IntelliJ IDEA.
 * User: binbin.wang
 * Date: 2017/11/2
 * Time: 10:54
 * To change this template use File | Settings | File Templates.
 * Description: Security configuration that adds {@link JWTFilter}
 */
@AllArgsConstructor
public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider tokenProvider;

    private final JwtSecurityProperties jwtProperties;

    private final AuthenticationManager authenticationManager;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        final JWTFilter jwtFilter = new JWTFilter(tokenProvider, jwtProperties);
        final JWTLoginFilter jwtLoginFilter = new JWTLoginFilter(jwtProperties.getUrl(), tokenProvider, jwtProperties, authenticationManager);
        jwtLoginFilter.setAuthenticationSuccessHandler();

        builder.addFilterBefore(jwtLoginFilter, UsernamePasswordAuthenticationFilter.class);
        builder.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

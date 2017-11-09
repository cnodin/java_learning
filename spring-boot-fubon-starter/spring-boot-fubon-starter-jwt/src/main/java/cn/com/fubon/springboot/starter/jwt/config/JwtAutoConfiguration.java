package cn.com.fubon.springboot.starter.jwt.config;

import cn.com.fubon.springboot.starter.jwt.auth.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: binbin.wang
 * Date: 2017/11/2
 * Time: 13:59
 * To change this template use File | Settings | File Templates.
 * Description:Main class that triggers auto configuration
 */
@Configuration
@ConditionalOnClass({
        WebSecurityConfigurerAdapter.class,
        AuthenticationManager.class,
        GlobalAuthenticationConfigurerAdapter.class
})
@EnableConfigurationProperties(JwtSecurityProperties.class)
public class JwtAutoConfiguration {

    private final JwtSecurityProperties jwtProperties;

    public JwtAutoConfiguration(JwtSecurityProperties jwtProperties){
        this.jwtProperties = jwtProperties;
    }

    @Bean
    @ConditionalOnMissingBean(TokenProvider.class)
    public TokenProvider tokenProvider(){
        return new JWTTokenProvider(jwtProperties);
    }

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    public UserDetailsService userDetailsService(){
        return new SimpleDetailsService();
    }

    /**
     * Default Jwt based security configuration
     */
    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @ConditionalOnProperty(prefix = "fubon.config.jwt", name = "enabled", matchIfMissing = true)
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        protected final SecurityProperties securityProperties;

        protected final JwtSecurityProperties jwtProperties;

        protected final PasswordEncoder passwordEncoder;

        protected final UserDetailsService userDetailsService;

        protected final TokenProvider tokenProvider;

        public SecurityConfiguration(SecurityProperties securityProperties,
                                     JwtSecurityProperties jwtProperties,
                                     PasswordEncoder passwordEncoder,
                                     UserDetailsService userDetailsService,
                                     TokenProvider tokenProvider){
            this.securityProperties = securityProperties;
            this.jwtProperties = jwtProperties;
            this.passwordEncoder = passwordEncoder;
            this.userDetailsService = userDetailsService;
            this.tokenProvider = tokenProvider;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            if (this.securityProperties.isRequireSsl()){
                http.requiresChannel().anyRequest().requiresSecure();
            }

            if (!this.securityProperties.isEnableCsrf()){
                http.csrf().disable();
            }

            http.exceptionHandling()
                    .authenticationEntryPoint(new Http401UnauthorizedEntryPoint())
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .apply(new JWTConfigurer(tokenProvider, jwtProperties, authenticationManagerBean()));

        }
    }

}

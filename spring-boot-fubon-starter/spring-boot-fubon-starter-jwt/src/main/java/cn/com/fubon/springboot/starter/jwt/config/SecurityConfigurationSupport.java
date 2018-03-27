package cn.com.fubon.springboot.starter.jwt.config;

import cn.com.fubon.springboot.starter.jwt.auth.*;
import cn.com.fubon.springboot.starter.jwt.config.JwtSecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 11/7/17
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class SecurityConfigurationSupport
				extends WebSecurityConfigurerAdapter
				implements EnvironmentAware {

	protected static final String LOGIN_ENDPOINT = "/auth/login";
	protected static final String LOGOUT_ENDPOINT = "/auth/logout";

	private static final String JWT_TOKEN_SECRET_KEY = "JWT_TOKEN_SECRET_KEY";
	private static final String REMEMBER_ME_TOKEN_SECRET_KEY = "REMEMBER_ME_TOKEN_SECRET_KEY";

	private Environment environment;

	private final JwtSecurityProperties jwtProperties;

	public SecurityConfigurationSupport(JwtSecurityProperties jwtProperties){
		this.jwtProperties = jwtProperties;
	}

	@Override
	public void setEnvironment (Environment environment) {
		this.environment = environment;
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint(ObjectMapper objectMapper){
		return new JwtAuthenticationEntryPoint(objectMapper);
	}

	public TokenProvider tokenProvider(){
		return new JWTTokenProvider(jwtProperties);
	}

	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(new JwtAuthenticationProvider());
		customizeAuthenticationManager(auth);
	}

	@Override
	public void configure (WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Override
	protected void configure (HttpSecurity http) throws Exception {
		AuthenticationEntryPoint authenticationEntryPoint = lookup(AuthenticationEntryPoint.class);

		http.csrf().disable().exceptionHandling()
						.authenticationEntryPoint(authenticationEntryPoint)
						.and()
						.sessionManagement()
						.sessionCreationPolicy(STATELESS);

		customizeRequestAuthorization(http.authorizeRequests()
						.antMatchers("/").permitAll()
						.antMatchers(POST, LOGIN_ENDPOINT).permitAll()
						.and());

		http.authorizeRequests().anyRequest().authenticated();

		JWTTokenProvider jwtTokenProvider = lookup(JWTTokenProvider.class);
		customizeFilters(http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
						LogoutFilter.class));

		customizeRememberMe(http);
	}

	protected void customizeAuthenticationManager(AuthenticationManagerBuilder auth) {
	}

	protected void customizeFilters(HttpSecurity http) {
	}

	protected void customizeRememberMe(HttpSecurity http) throws Exception {
	}

	protected void customizeRequestAuthorization(HttpSecurity http) throws Exception {
	}

	private <T> T lookup(String beanName){
		return (T)getApplicationContext().getBean(beanName);
	}

	private <T> T lookup(Class clazz){
		return (T)getApplicationContext().getBean(clazz);
	}
}

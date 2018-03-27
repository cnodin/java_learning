package cn.com.fubon.springboot.starter.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 11/7/17
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Configuration
public class AuthSecurityConfiguration extends SecurityConfigurationSupport {

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean () throws Exception {
		return super.authenticationManagerBean();
	}

	//public AuthenticationProvider usernamePasswordAuthenticationProvider(UserService)

	@Override
	protected void customizeAuthenticationManager (AuthenticationManagerBuilder auth) {
		super.customizeAuthenticationManager(auth);
	}

	@Override
	protected void customizeFilters (HttpSecurity http) {
		super.customizeFilters(http);
	}

	@Override
	protected void customizeRememberMe (HttpSecurity http) throws Exception {
		super.customizeRememberMe(http);
	}

	@Override
	protected void customizeRequestAuthorization (HttpSecurity http) throws Exception {
		super.customizeRequestAuthorization(http);
	}
}

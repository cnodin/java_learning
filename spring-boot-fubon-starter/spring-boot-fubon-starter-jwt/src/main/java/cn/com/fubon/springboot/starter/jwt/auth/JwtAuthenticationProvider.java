package cn.com.fubon.springboot.starter.jwt.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 11/7/17
 * Time: 10:58
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class JwtAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate (Authentication authentication)
					throws AuthenticationException {
		// JwtAuthenticationToken is always authenticated, as JwtAuthenticationFilter has already
		// checked them through JwtTokenService.
		return authentication;
	}

	@Override
	public boolean supports (Class<?> authentication) {
		return JwtAuthenticationToken.class.isAssignableFrom(authentication);
	}
}

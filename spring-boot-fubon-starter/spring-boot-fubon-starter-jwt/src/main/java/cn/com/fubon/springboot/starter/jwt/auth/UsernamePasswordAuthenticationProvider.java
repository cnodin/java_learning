package cn.com.fubon.springboot.starter.jwt.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 11/7/17
 * Time: 16:14
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate (Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String)authentication.getCredentials();


		return new UsernamePasswordAuthenticationToken(authentication.getName(),
												null,
																	authentication.getAuthorities());
	}

	@Override
	public boolean supports (Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication.getClass());
	}
}

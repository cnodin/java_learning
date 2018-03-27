package cn.com.fubon.springboot.starter.jwt.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 11/6/17
 * Time: 20:23
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Slf4j
public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private static final int ONE_DAY_MINUTES = 24 * 60;
	private static final String X_SET_AUTHORIZATION_BEARER_HEADER = "X-Set-Authorization-Bearer";

	private TokenProvider tokenProvider;

	public DefaultAuthenticationSuccessHandler(TokenProvider tokenProvider){
		this.tokenProvider = tokenProvider;
	}


	@Override
	public void onAuthenticationSuccess (HttpServletRequest request,
																			 HttpServletResponse response,
																			 Authentication authentication) throws IOException, ServletException {

		if (response.containsHeader(X_SET_AUTHORIZATION_BEARER_HEADER)){
			log.debug("{} has already been set.", X_SET_AUTHORIZATION_BEARER_HEADER);
			return ;
		}
		//登录成功，创建JWT
		String jwtToken = tokenProvider.createToken(authentication, true);
		response.setHeader(X_SET_AUTHORIZATION_BEARER_HEADER, jwtToken);
	}
}

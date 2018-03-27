package cn.com.fubon.springboot.starter.jwt.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 11/7/17
 * Time: 14:14
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class JwtAuthenticationEntryPoint
				extends DefaultAuthenticationFailureHandler
				implements AuthenticationEntryPoint {

	public JwtAuthenticationEntryPoint(ObjectMapper objectMapper){
		super(objectMapper);
	}

	@Override
	public void commence (HttpServletRequest request,
												HttpServletResponse response,
												AuthenticationException e)
					throws IOException, ServletException {
		onAuthenticationFailure(request, response, e);
	}
}

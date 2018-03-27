package cn.com.fubon.springboot.starter.jwt.auth;

import cn.com.fubon.springboot.starter.jwt.model.RestErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 11/6/17
 * Time: 20:39
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Slf4j
public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private final ObjectMapper objectMapper;

	public DefaultAuthenticationFailureHandler(ObjectMapper objectMapper){
		this.objectMapper = objectMapper;
	}

	@Override
	public void onAuthenticationFailure (HttpServletRequest request,
																			 HttpServletResponse response,
																			 AuthenticationException e) throws IOException, ServletException {
		log.warn("authentication failure, ex -> {}", e.getMessage());

		HttpStatus httpStatus = translateAuthenticationException(e);

		response.setStatus(httpStatus.value());
		response.setContentType(APPLICATION_JSON_VALUE);

		writeResponse(response.getWriter(), httpStatus, e);
	}

	protected HttpStatus translateAuthenticationException(AuthenticationException e){
		return UNAUTHORIZED;
	}

	protected void writeResponse(Writer writer, HttpStatus httpStatus,
															 AuthenticationException e) throws IOException {
		RestErrorResponse restErrorResponse = RestErrorResponse.of(httpStatus, e);

		objectMapper.writeValue(writer, restErrorResponse);
	}
}

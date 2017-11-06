package cn.com.fubon.springboot.starter.jwt.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: binbin.wang
 * Date: 2017/10/31
 * Time: 16:17
 * To change this template use File | Settings | File Templates.
 * Description: implements of {@link AuthenticationEntryPoint} that responds
 *    when unauthorized is being return by security part
 */
@Slf4j
public class Http401UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
      log.debug("Authentication entry point access denied");
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
    }
}

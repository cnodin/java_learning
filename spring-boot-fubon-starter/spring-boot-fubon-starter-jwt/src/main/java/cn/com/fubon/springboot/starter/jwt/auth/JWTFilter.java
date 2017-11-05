package cn.com.fubon.springboot.starter.jwt.auth;

import cn.com.fubon.springboot.starter.jwt.config.JwtSecurityProperties;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: binbin.wang
 * Date: 2017/11/2
 * Time: 10:59
 * To change this template use File | Settings | File Templates.
 * Description:{@link org.springframework.web.filter.GenericFilterBean} that checks
 *  if header contains token and add it to {@link org.springframework.security.core.context.SecurityContextHolder}
 */
@AllArgsConstructor
public class JWTFilter extends GenericFilterBean {

    private TokenProvider tokenProvider;

    private JwtSecurityProperties jwtProperties;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest)request;

        Optional.ofNullable(jwtProperties.getToken().getTokenHeader())
                .map(httpRequest::getHeader)
                .filter(StringUtils::hasText)
                .filter(x -> x.startsWith(jwtProperties.getToken().getTokenSchema()))
                .map(x -> x.substring(jwtProperties.getToken().getTokenSchema().length(), x.length()))
                .filter(this.tokenProvider::validateToken)
                .map(this.tokenProvider::getAuthentication)
                .ifPresent(SecurityContextHolder.getContext()::setAuthentication);

        filterChain.doFilter(request, response);
    }
}

package cn.com.fubon.springboot.starter.jwt.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 11/6/17
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String TOKEN_PREFIX = "Bearer";

	private final JWTTokenProvider jwtTokenProvider;

	public JwtAuthenticationFilter(JWTTokenProvider jwtTokenProvider){
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		Authentication authentication = getAuthentication(request);
		if (authentication == null){
			SecurityContextHolder.clearContext();
			filterChain.doFilter(request, response);

			return;
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}

	private Authentication getAuthentication(HttpServletRequest request){
		String authenticationHeader = request.getHeader(AUTHORIZATION_HEADER);
		if (StringUtils.isEmpty(authenticationHeader)){
			log.debug("Authenticatin header is empty");
			return null;
		}

		if (!StringUtils.substringMatch(authenticationHeader, 0, TOKEN_PREFIX)){
			log.debug("Token prefix {} in Authentication header was not found", TOKEN_PREFIX);
			return null;
		}
		String jwtToken = authenticationHeader.substring(TOKEN_PREFIX.length() + 1);

		return jwtTokenProvider.getAuthentication(jwtToken);
	}
}

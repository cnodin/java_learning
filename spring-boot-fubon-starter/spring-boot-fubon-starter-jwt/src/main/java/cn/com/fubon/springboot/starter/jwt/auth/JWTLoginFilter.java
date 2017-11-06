package cn.com.fubon.springboot.starter.jwt.auth;

import cn.com.fubon.springboot.starter.jwt.config.JwtSecurityProperties;
import cn.com.fubon.springboot.starter.jwt.model.AuthenticationRequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: binbin.wang
 * Date: 2017/11/2
 * Time: 11:17
 * To change this template use File | Settings | File Templates.
 * Description:Authentication entry point in the system
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private TokenProvider tokenProvider;

    private JwtSecurityProperties jwtSecurityProperties;

    protected JWTLoginFilter(String defaultFilterProcessUrl,
                            TokenProvider tokenProvider,
                            JwtSecurityProperties jwtSecurityProperties,
                            AuthenticationManager authenticationManager){
        super(new AntPathRequestMatcher(defaultFilterProcessUrl));
        this.tokenProvider = tokenProvider;
        this.jwtSecurityProperties = jwtSecurityProperties;
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        Optional<? extends AuthenticationRequestBody> requestBody = Try.of(()->
                Optional.ofNullable(new ObjectMapper()
                                        .readValue(request.getInputStream(),
                                                jwtSecurityProperties.getAuthenticationRequestBody()))
            ).recover(ex ->
                Optional.empty()
            ).get();

        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                requestBody.map(AuthenticationRequestBody::getLogin).orElse(null),
                requestBody.map(AuthenticationRequestBody::getPassword).orElse(null)
        );
        token.setDetails(requestBody.map(x -> x.isRememberMe()));

        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);

        boolean rememberMe = Try.of(authResult::getDetails)
                .map(x -> (Boolean)x)
                .recover(ex -> false)
                .get();

        final String token = tokenProvider.createToken(authResult, rememberMe);

        response.addHeader(jwtSecurityProperties.getToken().getTokenHeader(),
                jwtSecurityProperties.getToken().getTokenSchema() + token);
    }
}

package cn.com.fubon.springboot.starter.jwt.auth;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.security.core.Authentication;

/**
 * Created with IntelliJ IDEA.
 * User: binbin.wang
 * Date: 2017/10/31
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 * Description: service that manipulates with json web token
 */
public interface TokenProvider {

    /**
     * Creates token based on authentication details
     *
     * @param authentication    authentication details
     * @param rememberMe        is user remember me
     * @return  json web token
     */
    String createToken(Authentication authentication, Boolean rememberMe);

    /**
     * Checks whether token is valid
     *
     * @param token token to be checked
     * @return  is valid or not
     */
    boolean validateToken(String token);

    /**
     * Extract authentication from token
     *
     * @param token token to be extracted from
     * @return  authentication details
     */
    Authentication getAuthentication(String token);
}



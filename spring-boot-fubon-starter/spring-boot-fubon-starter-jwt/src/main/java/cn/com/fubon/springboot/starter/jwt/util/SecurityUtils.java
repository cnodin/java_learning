package cn.com.fubon.springboot.starter.jwt.util;

import cn.com.fubon.springboot.starter.jwt.common.AuthoritiesConstant;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;
import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

/**
 * Created with IntelliJ IDEA.
 * User: binbin.wang
 * Date: 2017/11/1
 * Time: 17:27
 * To change this template use File | Settings | File Templates.
 * Description: helper methods for security
 */
public final class SecurityUtils {

    private SecurityUtils(){throw new UnsupportedOperationException();}


    /**
     * get the login of the current user
     * @return the login of the current user
     */
    public static String getCurrentUserLogin(){
        return getCurrentAuthentication().map(
                x -> Match(x).of(
                        Case($(instanceOf(UserDetails.class)), o -> ((UserDetails)o).getUsername()),
                        Case($(instanceOf(String.class)), o -> (String)o.getPrincipal()),
                        Case($(), o -> null)
                )
        ).orElse(null);
    }

    /**
     * Check if a user is authenticated
     *
     * @return true if the user is authenticated, false otherwise
     */
    public static boolean isAuthenticated(){
        return getCurrentAuthentication().map(Authentication::getAuthorities)
                            .map(x -> x.stream().noneMatch(y -> y.getAuthority().equals(AuthoritiesConstant.ANONYMOUS)))
                            .orElse(false);
    }

    /**
     * if the current user has a specific authority (security role)
     *
     * @param authority the authority to check
     * @return true if the current user has the authority, fase otherwise
     */
    public static boolean isCurrentUserInRole(final String authority){
        return getCurrentAuthentication()
                .map(Authentication::getPrincipal)
                .filter(x -> x instanceof UserDetails)
                .map(x -> (UserDetails)x)
                .map(UserDetails::getAuthorities)
                .map(x -> x.contains(new SimpleGrantedAuthority(authority)))
                .orElse(false);
    }

    private static Optional<Authentication> getCurrentAuthentication() {
        return Optional.of(SecurityContextHolder.getContext()).
                map(SecurityContext::getAuthentication);
    }
}

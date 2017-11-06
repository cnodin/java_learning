package cn.com.fubon.springboot.starter.jwt.auth;

import cn.com.fubon.springboot.starter.jwt.model.SimpleUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: binbin.wang
 * Date: 2017/11/2
 * Time: 11:47
 * To change this template use File | Settings | File Templates.
 * Description: Simple user details service created just so execution does not break
 */
@Service
public class SimpleDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<String> userName = Optional.ofNullable(s);

        return userName.map(x -> new SimpleUserDetails(s, s, Collections.emptyList()))
                    .orElse(null);
    }
}

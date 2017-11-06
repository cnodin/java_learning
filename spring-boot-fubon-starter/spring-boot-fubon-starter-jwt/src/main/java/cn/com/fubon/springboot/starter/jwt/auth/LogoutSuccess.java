package cn.com.fubon.springboot.starter.jwt.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by fan.jin on 2017-05-06.
 */
@Slf4j
@Component
public class LogoutSuccess implements LogoutSuccessHandler {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        //R r =  R.ok().put("result", "success");
        //response.setContentType("application/json");
        //response.getWriter().write( objectMapper.writeValueAsString( r.toString() ) );
        //response.setStatus(HttpServletResponse.SC_OK);
        if (authentication != null)
            log.info(authentication.getPrincipal() + " logouted");
        else
            log.info("Unknown somebody logouted");
    }


}
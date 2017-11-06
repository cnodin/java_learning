package cn.com.fubon.jwtdemo.controller;

import cn.com.fubon.springboot.starter.jwt.auth.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: binbin.wang
 * Date: 2017/11/2
 * Time: 17:14
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@RestController
@RequestMapping("/")
public class HelloController {

    @Autowired
    private TokenProvider tokenProvider;

    @RequestMapping("/hello")
    public String hello(){
        return "hello, jwt";
    }

    @RequestMapping("/login")
    public String login(){
        return "do login";
    }

}

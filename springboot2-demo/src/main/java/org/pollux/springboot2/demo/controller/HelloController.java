package org.pollux.springboot2.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2019-04-10
 * Time: 11:18
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello8";
    }

}

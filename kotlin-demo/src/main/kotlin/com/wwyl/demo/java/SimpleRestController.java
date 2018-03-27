package com.wwyl.demo.java;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 27/03/2018
 * Time: 00:03
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@RestController
@RequestMapping("/java")
public class SimpleRestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello, java";
    }

    @GetMapping("/add")
    public int sum(int a, int b) {
//        BasicKotlinKt basicKotlinKt = new BasicKotlinKt();
//        BasicKotlin basicKotlin = new BasicKotlin();
        return a + b;
    }

}

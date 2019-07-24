package org.pollux.springbootdemo.springbootdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangbinbin
 * @version HelloController, 2019/5/28 14:59 wangbinbin Exp
 */
@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    return "hello";
  }

}

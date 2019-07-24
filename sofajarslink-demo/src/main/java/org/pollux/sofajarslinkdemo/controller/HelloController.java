package org.pollux.sofajarslinkdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangbinbin
 * @version HelloController, 2019/5/14 7:58 wangbinbin Exp
 */
@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    return "hello";
  }

}

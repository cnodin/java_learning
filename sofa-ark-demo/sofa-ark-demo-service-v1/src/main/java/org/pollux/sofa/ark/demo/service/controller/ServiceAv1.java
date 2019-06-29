package org.pollux.sofa.ark.demo.service.controller;

import org.pollux.sofa.ark.demo.myjar.DemoUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangbinbin
 * @version ServiceAv1, 2019/6/29 8:44 wangbinbin Exp
 */
@RestController
public class ServiceAv1 {

  @GetMapping("/test")
  public String test() {
    DemoUtil demoUtil = new DemoUtil();
    demoUtil.test();
    return "run myjar version1";
  }

}

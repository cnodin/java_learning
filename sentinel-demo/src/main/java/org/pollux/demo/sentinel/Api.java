package org.pollux.demo.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;

/**
 * @author wangbinbin
 * @version Api, 2019/9/20 20:03 wangbinbin Exp
 */
public class Api {

  @SentinelResource("HelloWorld")
  public void helloWorld() {
    System.out.println("hello world");
  }

}

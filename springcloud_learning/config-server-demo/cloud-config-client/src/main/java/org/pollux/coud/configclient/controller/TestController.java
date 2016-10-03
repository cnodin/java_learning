package org.pollux.coud.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pollux on 16/10/3.
 */
@RefreshScope
@RestController
public class TestController {

  @Value("${from}")
  private String from;


  @RequestMapping("/from")
  public String from(){

    return this.from;
  }

}

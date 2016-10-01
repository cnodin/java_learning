package org.pollux.cloud.compute.service.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by spockwwang on 2016/9/29.
 */
@RestController
@Getter
@Setter
@Log
public class ComputeController {

  @Autowired
  private DiscoveryClient client;

  @RequestMapping(value="/add", method = RequestMethod.GET)
  public Integer add(@RequestParam Integer a, @RequestParam Integer b){
    //ServiceInstance instance =
    ServiceInstance instance = client.getLocalServiceInstance();

    Integer r = a + b;

    log.info("/add, host:" + instance.getHost() + ", service_id:" +
            instance.getServiceId() + ", result:" + r);

    return r;
  }

}

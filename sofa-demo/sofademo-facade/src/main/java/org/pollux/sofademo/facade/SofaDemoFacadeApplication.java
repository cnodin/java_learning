package org.pollux.sofademo.facade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author wangbinbin
 * @version SofaDemoFacadeApplication, 2019/5/13 17:29 wangbinbin Exp
 */

@EnableDiscoveryClient
@EnableFeignClients("org.pollux")
@SpringBootApplication(scanBasePackages = "org.pollux")
public class SofaDemoFacadeApplication {

  public static void main(String[] args) {
    SpringApplication.run(SofaDemoFacadeApplication.class, args);
  }

}

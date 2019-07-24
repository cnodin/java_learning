package org.pollux.sofademo.app1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author wangbinbin$
 * @version SofaDemoApp1: SofaDemoApp1$, 2019/5/13$ 12:55$ wangbinbin$ Exp$
 */
@SpringBootApplication(scanBasePackages = "org.pollux")
@EnableFeignClients
@EnableDiscoveryClient
public class SofaDemoApp1 {

  public static void main(String[] args) {
    SpringApplication.run(SofaDemoApp1.class, args);
  }

}

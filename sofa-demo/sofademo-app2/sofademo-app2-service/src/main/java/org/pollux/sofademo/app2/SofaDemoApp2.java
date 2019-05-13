package org.pollux.sofademo.app2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author wangbinbin$
 * @version SofaDemoApp2: SofaDemoApp2$, 2019/5/13$ 14:07$ wangbinbin$ Exp$
 */
@SpringBootApplication(scanBasePackages = "org.pollux")
@EnableFeignClients("org.pollux")
@EnableDiscoveryClient
public class SofaDemoApp2 {

  public static void main(String[] args) {
    SpringApplication.run(SofaDemoApp2.class, args);
  }

}

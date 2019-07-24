package org.pollux.feigndemo.api;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2019-05-02
 * Time: 17:11
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Configuration
public class HelloFeignServiceConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}

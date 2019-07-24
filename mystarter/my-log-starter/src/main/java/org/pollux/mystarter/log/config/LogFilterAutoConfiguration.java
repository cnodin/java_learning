package org.pollux.mystarter.log.config;

import org.pollux.mystarter.log.LogFilter;
import org.pollux.mystarter.log.LogFilterRegistrationBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2019-05-26
 * Time: 21:35
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Configuration
@ConditionalOnClass({LogFilterRegistrationBean.class, LogFilter.class})
public class LogFilterAutoConfiguration {

    @Bean
    public LogFilterRegistrationBean logFilterRegistrationBean() {
        return new LogFilterRegistrationBean();
    }

}

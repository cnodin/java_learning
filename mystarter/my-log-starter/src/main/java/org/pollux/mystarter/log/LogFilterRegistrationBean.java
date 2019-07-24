package org.pollux.mystarter.log;

import org.springframework.boot.web.servlet.FilterRegistrationBean;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2019-05-26
 * Time: 21:43
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class LogFilterRegistrationBean extends FilterRegistrationBean<LogFilter> {

    public LogFilterRegistrationBean() {
        super();
        this.setFilter(new LogFilter());
        this.addUrlPatterns("/*");
        this.setName("LogFilter");
        this.setOrder(1);
    }

}

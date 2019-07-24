package org.pollux.mystarter.log.annotation;

import org.pollux.mystarter.log.config.LogFilterAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2019-05-26
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(LogFilterAutoConfiguration.class)
public @interface EnableLogFilter {
}

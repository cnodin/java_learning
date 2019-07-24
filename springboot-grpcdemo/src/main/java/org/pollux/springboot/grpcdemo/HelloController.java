package org.pollux.springboot.grpcdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2018/9/22
 * Time: 15:18
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

}

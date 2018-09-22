package org.pollux.docker.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2018/9/16
 * Time: 19:51
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@RestController
public class DockerController {

    @RequestMapping("/")
    public String index() {
        return "hello, docker!";
    }
}

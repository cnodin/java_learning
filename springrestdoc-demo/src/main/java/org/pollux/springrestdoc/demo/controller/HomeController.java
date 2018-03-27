package org.pollux.springrestdoc.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 07/12/2017
 * Time: 13:16
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@RestController
public class HomeController {

    @GetMapping("/home")
    public Map greeting() {
        return Collections.singletonMap("message", "Hello world");
    }

}

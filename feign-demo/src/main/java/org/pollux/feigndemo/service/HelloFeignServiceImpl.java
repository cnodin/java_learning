package org.pollux.feigndemo.service;

import org.pollux.feigndemo.api.HelloFeignService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2019-05-02
 * Time: 17:20
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Service
public class HelloFeignServiceImpl implements HelloFeignService {
    @Override
    @GetMapping(value = "/search/github")
    public String searchRepo(@RequestParam("q") String queryStr) {
        return "this is local method";
    }
}

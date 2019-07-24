package org.pollux.feigndemo.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2019-05-02
 * Time: 17:03
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@FeignClient(name="github-client", url="https://api.github.com", configuration = HelloFeignServiceConfig.class)
public interface HelloFeignService {

    @RequestMapping(value = "/search/repositories", method = RequestMethod.GET)
    String searchRepo(@RequestParam("q") String queryStr);
}

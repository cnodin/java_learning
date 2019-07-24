package org.pollux.sofademo.app1.api;

import org.pollux.sofademo.app1.api.fallback.UserApiFallbackFactory;
import org.pollux.sofademo.app1.model.UserDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wangbinbin$
 * @version UserApi: UserApi$, 2019/5/13$ 12:57$ wangbinbin$ Exp$
 */

@FeignClient(value = "sofademo-app1", fallbackFactory = UserApiFallbackFactory.class)
//@RequestMapping("/v1/user")
public interface UserApi {

  @GetMapping("/v1/user/{id}")
  UserDTO find(@PathVariable("id") Long id);
}

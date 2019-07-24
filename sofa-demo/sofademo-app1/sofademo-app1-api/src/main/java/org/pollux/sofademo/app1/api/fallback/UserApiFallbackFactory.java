package org.pollux.sofademo.app1.api.fallback;

import feign.hystrix.FallbackFactory;
import org.pollux.sofademo.app1.api.UserApi;
import org.pollux.sofademo.app1.model.UserDTO;
import org.springframework.stereotype.Component;

/**
 * @author wangbinbin$
 * @version UserApiFallbackFactory: UserApiFallbackFactory$, 2019/5/13$ 16:11$ wangbinbin$ Exp$
 */
@Component
public class UserApiFallbackFactory implements FallbackFactory<UserApi> {
  @Override
  public UserApi create(Throwable throwable) {
    return new UserApi() {
      @Override
      public UserDTO find(Long id) {
        return null;
      }
    };
  }
}

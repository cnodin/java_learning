package org.pollux.sofademo.app1.controller;

import lombok.extern.slf4j.Slf4j;
import org.pollux.sofademo.app1.api.UserApi;
import org.pollux.sofademo.app1.model.UserDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangbinbin$
 * @version UserController: UserController$, 2019/5/13$ 13:55$ wangbinbin$ Exp$
 */

@Slf4j
@RestController
public class UserController implements UserApi {

  @Override
  public UserDTO find(@PathVariable("id") Long id) {

    log.info("userid: {}", id);

    return UserDTO.build();
  }
}

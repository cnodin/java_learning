package org.pollux.sofademo.app1.model;

import lombok.Data;

/**
 * @author wangbinbin$
 * @version UserDTO: UserDTO$, 2019/5/13$ 12:56$ wangbinbin$ Exp$
 */
@Data
public class UserDTO {

  private String name;

  private String gender;

  public static UserDTO build() {
    UserDTO user = new UserDTO();
    user.setName("from app1");
    user.setGender("female");
    return user;
  }
}

package org.pollux.sofademo.app2.controller;

import lombok.extern.slf4j.Slf4j;
import org.pollux.sofademo.app1.api.UserApi;
import org.pollux.sofademo.app1.model.UserDTO;
import org.pollux.sofademo.app2.api.ProductApi;
import org.pollux.sofademo.app2.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangbinbin$
 * @version ProductController: ProductController$, 2019/5/13$ 14:47$ wangbinbin$ Exp$
 */
@Slf4j
@RestController
public class ProductController implements ProductApi {

  @Autowired
  private UserApi userApi;

  @Override
  public ProductDTO find(@PathVariable("id") Long id) {

    log.info("product id: {}", id);

    ProductDTO product = ProductDTO.build();

    UserDTO user = userApi.find(id);
    product.setUser(user);

    return product;
  }
}

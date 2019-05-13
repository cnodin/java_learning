package org.pollux.sofademo.facade.controller;

import org.pollux.sofademo.app2.api.ProductApi;
import org.pollux.sofademo.app2.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangbinbin
 * @version HelloFacadeController, 2019/5/13 17:35 wangbinbin Exp
 */
@RestController
@RequestMapping("/v1/facade")
public class HelloFacadeController {

  @Autowired
  private ProductApi productApi;

  @GetMapping("/product/{id}")
  public ProductDTO findProduct(@PathVariable("id") Long id) {
    return productApi.find(id);
  }

}

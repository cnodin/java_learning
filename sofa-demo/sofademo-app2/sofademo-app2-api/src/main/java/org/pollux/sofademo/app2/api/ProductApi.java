package org.pollux.sofademo.app2.api;

import org.pollux.sofademo.app2.model.ProductDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wangbinbin$
 * @version ProductApi: ProductApi$, 2019/5/13$ 14:45$ wangbinbin$ Exp$
 */
@FeignClient("sofademo-app2")
//@RequestMapping("/v1/product")
public interface ProductApi {

  @GetMapping("/v1/product/{id}")
  ProductDTO find(@PathVariable("id") Long id);
}

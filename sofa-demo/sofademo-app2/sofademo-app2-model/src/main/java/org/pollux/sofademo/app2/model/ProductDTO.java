package org.pollux.sofademo.app2.model;

import lombok.Data;
import org.pollux.sofademo.app1.model.UserDTO;

/**
 * @author wangbinbin$
 * @version ProductDTO: ProductDTO$, 2019/5/13$ 14:26$ wangbinbin$ Exp$
 */
@Data
public class ProductDTO {

  private String productName;

  private String location;

  private UserDTO user;

  public static ProductDTO build() {
    ProductDTO product = new ProductDTO();
    product.setProductName("phone");
    product.setLocation("from app2");

    product.setUser(UserDTO.build());
    return product;
  }

}

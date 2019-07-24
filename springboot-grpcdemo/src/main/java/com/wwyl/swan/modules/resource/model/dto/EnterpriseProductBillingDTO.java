package com.wwyl.swan.modules.resource.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 产品线计费方式DTO<br/>
 * Date：2018-6-4 23:14:01<br/>
 * Author：sh.zhang<br/>
 * */
@Getter
@Setter
public class EnterpriseProductBillingDTO {

    private String billingName;
    private Double billingValue;

    /**
     * 计费值出现时间
     */
    private String billingTime;

}

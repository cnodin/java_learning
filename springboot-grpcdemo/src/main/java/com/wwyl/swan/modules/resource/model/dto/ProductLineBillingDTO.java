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
public class ProductLineBillingDTO {
    private Long productLineBillingId;
    private String productLineBillingCode;
    private String productLineBillingName;
    private String productLineName;
    private String productLineCode;
    private Long productLineId;
    private Long enterpriseProductLineId;
}

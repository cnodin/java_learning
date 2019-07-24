package com.wwyl.swan.modules.resource.model.dto.external;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: yongming
 * Date: 04/05/2018
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Getter
@Setter
public class UrlTopDTO {


//    private String url;//当前域名
//    private Integer totalRequest;//请求热度
//    private Double totalFlow;//流量

    @JSONField(ordinal = 1, name = "url")
    private String url;

    @JSONField(ordinal = 2, name = "rq")
    private Integer totalRequest;

    @JSONField(ordinal = 3, name = "tf")
    private Double totalFlow;


}

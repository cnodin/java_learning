package com.wwyl.swan.modules.resource.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;


/**
 * Created with IntelliJ IDEA.
 * user yongming
 * Date: 04/05/2018
 * Time: 20:41
 * To change this template use File | Settings | File Templates.
 * Description: 响应
 *
 * @author spockwang
 */
@Setter
@Getter
public class NetworkResourceTopResponse {

    @JSONField(ordinal = 1)
    private String url;

    @JSONField(ordinal = 2)
    private Integer totalRequest;

    @JSONField(ordinal = 3)
    private Double totalFlow;



//    private String url;//当前域名
//    private Integer totalRequest;//请求热度
//    private Long totalFlow;//流量
}

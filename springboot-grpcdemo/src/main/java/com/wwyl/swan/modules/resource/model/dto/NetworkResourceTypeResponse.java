package com.wwyl.swan.modules.resource.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * Date: 25/11/2017
 * Time: 09:48
 * To change this template use File | Settings | File Templates.
 * Description: 网络资源分类响应
 *
 * @author spockwang
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NetworkResourceTypeResponse {

    @JSONField(ordinal = 1)
    private String code;

    @JSONField(ordinal = 2)
    private String name;

    @JSONField(ordinal = 3)
    private String value;
}

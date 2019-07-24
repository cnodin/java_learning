package com.wwyl.swan.modules.content.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 28/11/2017
 * Time: 14:44
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Getter
@Setter
public class SwanTaskFirstDomainResponse {

    @JSONField(ordinal = 0)
    private String result;

    @JSONField(ordinal = 1)
    private SecondDomainDto data;
}

package com.wwyl.swan.modules.resource.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 01/12/2017
 * Time: 19:23
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Getter
@Setter
public class FlowStatusDTO {

    @JSONField(ordinal = 1)
    private Map<String, Integer> all;

    @JSONField(ordinal = 2)
    private Map<String, Integer> hit;

    @JSONField(ordinal = 3)
    private Map<String, Integer> origin;
}

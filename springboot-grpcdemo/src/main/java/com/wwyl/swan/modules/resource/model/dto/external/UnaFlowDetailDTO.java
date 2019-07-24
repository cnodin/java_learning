package com.wwyl.swan.modules.resource.model.dto.external;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 29/11/2017
 * Time: 15:52
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Getter
@Setter
public class UnaFlowDetailDTO {
    @JSONField(ordinal = 1,name = "tf_all")
    private Map<String, Double> flows;

    @JSONField(ordinal = 2,name = "tf_down")
    private Map<String, Double> flowsDown;

    @JSONField(ordinal = 3,name = "tf_up")
    private Map<String, Double> flowsUp;

    @JSONField(ordinal = 4,name = "bw_all")
    private Map<String, Double> bandWidths;

    @JSONField(ordinal = 5,name = "bw_down")
    private Map<String, Double> bandWidthsDown;

    @JSONField(ordinal = 6,name = "bw_up")
    private Map<String, Double> bandWidthsUp;
}

package com.wwyl.swan.modules.resource.model.dto.external;

import com.alibaba.fastjson.annotation.JSONField;
import com.wwyl.swan.modules.resource.model.dto.FlowStatusDTO;
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
public class FlowDetailDTO {
    @JSONField(ordinal = 1, name = "tf")
    private Map<String, Double> flows;

    @JSONField(ordinal = 2, name = "rq")
    private Map<String, Long> requests;

    @JSONField(ordinal = 3, name = "bw")
    private Map<String, Double> bandWidths;

    @JSONField(ordinal = 4, name = "st")
    private Map<String, FlowStatusDTO> codeStatus;
}

package com.wwyl.swan.modules.resource.model.dto.external;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 29/11/2017
 * Time: 15:47
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Getter
@Setter
public class UnaFlowGetDetailResponse {

    @JSONField(ordinal = 0)
    private String result;

    @JSONField(ordinal = 1)
    private UnaFlowDetailDTO data;
}

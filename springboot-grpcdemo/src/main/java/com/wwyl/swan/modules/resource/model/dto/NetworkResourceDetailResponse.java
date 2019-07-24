package com.wwyl.swan.modules.resource.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Date: 25/11/2017
 * Time: 09:48
 * To change this template use File | Settings | File Templates.
 * Description: 网络资源明细响应
 *
 * @author spockwang
 */

@Getter
@Setter

public class NetworkResourceDetailResponse {

    @JSONField(ordinal = 1)
    private Map<String, Double> flows;

    @JSONField(ordinal = 2)
    private Map<String, Long> requests;

    @JSONField(ordinal = 3)
    private Map<String, Double> bandWidths;

    @JSONField(ordinal = 4, name = "status")
    private Map<String, FlowStatusDTO> codeStatus;

    @JSONField(ordinal = 5)
    private String totalFlow;

    @JSONField(ordinal = 6)
    private Long totalRequest;

    @JSONField(ordinal = 7)
    private Double maxBandWidth;

    /**
     *  峰值时间
     */
    @JSONField(ordinal = 8)
    private String maxBandWidthTime;
}

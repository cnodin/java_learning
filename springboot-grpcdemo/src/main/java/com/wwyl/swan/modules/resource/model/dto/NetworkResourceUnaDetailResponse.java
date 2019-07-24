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
 * Description: una网络资源明细响应
 *
 * @author spockwang
 */

@Getter
@Setter

public class NetworkResourceUnaDetailResponse {

    @JSONField(ordinal = 1)
    private Map<String, Double> flows;

    @JSONField(ordinal = 2)
    private Map<String, Double> flowsDown;

    @JSONField(ordinal = 3)
    private Map<String, Double> flowsUp;

    @JSONField(ordinal = 4)
    private Map<String, Double> bandWidths;

    @JSONField(ordinal = 5)
    private Map<String, Double> bandWidthsDown;

    @JSONField(ordinal = 6)
    private Map<String, Double> bandWidthsUp;

    @JSONField(ordinal = 7)
    private String totalFlows;

    @JSONField(ordinal = 8)
    private String totalFlowsDown;

    @JSONField(ordinal = 9)
    private String totalFlowsUp;

    @JSONField(ordinal = 10)
    private String maxBandWidths;

    @JSONField(ordinal = 11)
    private String maxBandWidthsDown;

    @JSONField(ordinal = 12)
    private String maxBandWidthsUp;

    @JSONField(ordinal = 13)
    private String maxBandWidthsTime;

    @JSONField(ordinal = 14)
    private String maxBandWidthsDownTime;

    @JSONField(ordinal = 15)
    private String maxBandWidthsUpTime;
}

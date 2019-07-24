package com.wwyl.swan.modules.resource.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 25/11/2017
 * Time: 09:48
 * To change this template use File | Settings | File Templates.
 * Description:
 */

@Getter
@Setter
public class NetworkResourceGeneralResponse {

    @JSONField(ordinal = 1)
    private String totalFlow;

    @JSONField(ordinal = 2)
    private Long totalRequest;

    @JSONField(ordinal = 3)
    private Double maxBandWidth;

    @JSONField(ordinal = 4)
    private Map<String, Double> bandWidths;

    /**
     *  最大带宽平均值
     */
    @JSONField(ordinal = 5)
    private Double avgBandWidth;

    /**
     *  峰值时间
     */
    @JSONField(ordinal = 6)
    private String maxBandWidthTime;


    /**
     * 计费方式，计费值
     */
    @JSONField(ordinal = 7)
    private List<EnterpriseProductBillingDTO> billing;

    @JSONField(ordinal = 8)
    private Map<String, Double> tfs;
}

package com.wwyl.swan.modules.resource.model.dto.external;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 28/11/2017
 * Time: 14:49
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Getter
@Setter
public class FlowTotalDTO {

    @JSONField(ordinal = 1, name = "tf_all")
    private String tfAll;

    @JSONField(ordinal = 2, name = "rq_all")
    private Long rqAll;

    @JSONField(ordinal = 3, name = "bw_max")
    private Double bwMax;

    @JSONField(ordinal = 4)
    private Map<String, Double> bw;

    @JSONField(ordinal = 5, name = "bw_avg")
    private Double bwAvg;

    @JSONField(ordinal = 6, name = "bw_max_time")
    private String bwMaxTime;

    @JSONField(ordinal = 7, name = "tf_up")
    private String tfUp;

    @JSONField(ordinal = 8, name = "tf_down")
    private String tfDown;

    @JSONField(ordinal = 9, name = "bw_up_max")
    private String bwUpMax;

    @JSONField(ordinal = 10, name = "bw_up_max_time")
    private String bwUpMaxTime;

    @JSONField(ordinal = 11, name = "bw_down_max")
    private String bwDownMax;

    @JSONField(ordinal = 12, name = "bw_down_max_time")
    private String bwDownMaxTime;

    @JSONField(ordinal = 13)
    private Map<String, Double> tf;

}

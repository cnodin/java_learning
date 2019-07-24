package com.wwyl.swan.common.enums;

/**
 * 产品线计费方式枚举<br/>
 * Date：2018-6-5 14:45:56<br/>
 * Author：sh.zhang<br/>
 * */
public enum ProductLineBillingEnum {
    TOTAL_FLOW("total_flow", "总流量"),
    FIRST_BW("first_bw", "第一峰值"),
    MAX_AVG_BW("max_avg_bw", "带宽峰值平均值"),
    FOURTH_BW("fourth_bw", "带宽峰值平均值"),
    REQUEST_TOTAL("request_total", "请求数")
    ;

    private String code;
    private String name;

    private ProductLineBillingEnum (String code, String name) {
        this.name = name;
        this.code = code;
    }

    public String getName () {
        return this.name;
    }

    public String getCode () {
        return this.code;
    }

}

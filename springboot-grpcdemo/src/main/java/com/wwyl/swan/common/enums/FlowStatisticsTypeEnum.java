package com.wwyl.swan.common.enums;

/**
 * 流量统计类型枚举<br/>
 * Date：2018-7-23 20:07:57<br/>
 * Author：sh.zhang<br/>
 * */
public enum FlowStatisticsTypeEnum {
    TOTAL((short)0),
    DETAIL((short)1),
    ;

    private Short type;

    private FlowStatisticsTypeEnum(Short type) {
        this.type = type;
    }

    public Short getType() {
        return this.type;
    }
}

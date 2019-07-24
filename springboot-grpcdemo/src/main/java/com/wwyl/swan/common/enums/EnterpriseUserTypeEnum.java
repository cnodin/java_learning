package com.wwyl.swan.common.enums;

/**
 * 企业用户类型枚举<br/>
 * Date：2018-8-6 17:14:25<br/>
 * Author：sh.zhang<br/>
 * */
public enum EnterpriseUserTypeEnum {
    ADMIN(0),
    OTHER(1)
    ;

    private Integer type;

    private EnterpriseUserTypeEnum (Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}

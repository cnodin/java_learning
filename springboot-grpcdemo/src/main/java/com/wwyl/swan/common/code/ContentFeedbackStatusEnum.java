package com.wwyl.swan.common.code;

/**
 * 内容预取反馈状态枚举<br/>
 * Date：2018-4-25 14:59:29<br/>
 * Author：sh.zhang<br/>
 * */
public enum ContentFeedbackStatusEnum {
    SUCCESS((short)1),
    FAIL((short)0),
    TIMEOUT((short)2);

    private Short status;
    private ContentFeedbackStatusEnum(Short status) {
        this.status = status;
    }

    public Short getStatus () {
        return this.status;
    }
}

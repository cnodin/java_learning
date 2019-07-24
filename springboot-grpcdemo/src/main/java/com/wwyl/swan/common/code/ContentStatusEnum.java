package com.wwyl.swan.common.code;

/**
 * 内容预取状态枚举<br/>
 * Date：2018-4-25 14:59:29<br/>
 * Author：sh.zhang<br/>
 * */
public enum ContentStatusEnum {
    IN_PROGRESS((short)1),
    NOT_START((short)0),
    FINISHED((short)2),
    FAIL((short)3);

    private Short status;
    private ContentStatusEnum(Short status) {
        this.status = status;
    }

    public Short getStatus () {
        return this.status;
    }
}

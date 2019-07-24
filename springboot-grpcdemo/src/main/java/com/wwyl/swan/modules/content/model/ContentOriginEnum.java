package com.wwyl.swan.modules.content.model;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 04/12/2017
 * Time: 15:03
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Getter
public enum ContentOriginEnum {

    /**
     * 来源客户系统
     */
    SWAN(0),

    /**
     * 来源FTP
     */
    FTP(1),

    /**
     * 来源openAPI
     */
    OPENAPI(2);

    private Integer value;

    private ContentOriginEnum(Integer value){
        this.value = value;
    }

    public Integer getCode() {
        return value;
    }

}

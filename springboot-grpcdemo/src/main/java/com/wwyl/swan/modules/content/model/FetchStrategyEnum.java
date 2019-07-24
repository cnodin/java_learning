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
public enum FetchStrategyEnum {

    /**
     * 静态父
     */
    STATIC(0),

    /**
     * 二级域名
     */
    SECONDDOMAIN(1);

    private Integer value;

    private FetchStrategyEnum(Integer value){
        this.value = value;
    }

    public Integer getCode() {
        return value;
    }

}

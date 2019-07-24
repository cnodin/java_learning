package com.wwyl.swan.modules.resource.model;

/**
 * Created with IntelliJ IDEA.
 * User: cnpollux
 * Date: 09/02/2018
 * Time: 17:38
 * To change this template use File | Settings | File Templates.
 * Description: 请求带宽类型
 */
public enum BandWidthTypeEnum {
    /**
     * CDN
     */
    CDN("cdn"),

    /**
     * UNA
     */
    UNA("una"),

    /**
     * UNA
     */
    SCT("sct");

    private String value;

    private BandWidthTypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}

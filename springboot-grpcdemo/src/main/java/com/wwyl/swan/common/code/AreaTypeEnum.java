package com.wwyl.swan.common.code;

/**
 * Created with IntelliJ IDEA.
 * User: miyitang
 * Date: 23/01/2018
 * Time: 09:13
 * Description: 流量节点类型
 */
public enum AreaTypeEnum {

    /**
     * 省份
     */
    PROVINCE("province"),

    /**
     * 国家
     */
    COUNTRY("country")
    ;

    private String code;

    private AreaTypeEnum(String code){
        this.code = code;
    }


    @Override
    public String toString() {
        return this.code;
    }
}

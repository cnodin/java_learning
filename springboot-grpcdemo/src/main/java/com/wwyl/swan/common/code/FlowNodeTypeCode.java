package com.wwyl.swan.common.code;

/**
 * Created with IntelliJ IDEA.
 * User: miyitang
 * Date: 23/01/2018
 * Time: 09:13
 * Description: 流量节点类型
 */
public enum FlowNodeTypeCode {

    /**
     * 边缘流量节点
     */
    FLOW_NODE_EDGE("0"),

    /**
     * 中间缓存流量
     */
    FLOW_NODE_CACHE_1("1"),

    FLOW_NODE_CACHE_2("2")
    ;

    private String code;

    private FlowNodeTypeCode(String code){
        this.code = code;
    }


    @Override
    public String toString() {
        return this.code;
    }


}

package com.wwyl.swan.modules.content.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 28/11/2017
 * Time: 14:44
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Getter
@Setter
public class SwanTaskFirstDomainRequest {

    private String taskNo;

    private String status;

    private String firstDomain;

    private String taskType;

    /**
     * 签名值
     */
    private String sign;

    private Long stamp;
}

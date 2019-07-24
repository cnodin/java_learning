package com.wwyl.swan.modules.content.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Date：2018-4-25 13:26:15<br/>
 * */
@Getter
@Setter
public class ContentDetailDto {

    private String uri;

    private String md5;

    private ContentPushTypeEnum pushType;

    private Integer serverNumber;
    private String customerDomain;
    private String secondDomain;
    private String firstDomain;

    private String enterpriseCode;
    private Long enterpriseId;
    private Integer fetchStrategy;

}

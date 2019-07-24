package com.wwyl.swan.modules.content.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnterpriseFirstDomainResponse {

    private Long enterpriseId;

    private String enterpriseCode;

    private String domain;

    private String firstDomain;

    private String secondDomain;

    private Integer fetchStrategy;
}

package com.wwyl.swan.modules.content.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 根据一级域名从turkey获取对应二级域名和主机数量
 */
@Getter
@Setter
public class SecondDomainDto implements Serializable{

    private static final long serialVersionUID = 1L;

    private String secondDomain;

    private Integer cacheNumber;

    private Integer shieldNumber;

    private List<String> cacheList;
    private List<String> shieldList;
}

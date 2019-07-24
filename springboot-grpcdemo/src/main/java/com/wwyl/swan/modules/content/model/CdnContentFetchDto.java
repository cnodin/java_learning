package com.wwyl.swan.modules.content.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Date：2018-4-25 13:26:15<br/>
 * */
@Getter
@Setter
public class CdnContentFetchDto {

    private String enterpriseCode;
    /**
     * 内容详情
     */
    private List<ContentDetailDto> content;
}

package com.wwyl.swan.modules.content.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Date：2018-4-25 13:26:15<br/>
 * */
@Getter
@Setter
public class CdnContentPushDto {

    @Enumerated(EnumType.STRING)
    @NotNull
    private ContentOperationTypeEnum operationtype;

    private String enterpriseCode;

    /**
     * 内容详情
     */
    private List<ContentDetailDto> content;
}

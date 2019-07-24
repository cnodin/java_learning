package com.wwyl.swan.modules.content.model;

import com.wwyl.lark.core.entity.BaseLongEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 02/12/2017
 * Time: 09:17
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Getter
@Setter
@Table(name="content")
@Entity
public class ContentEntity extends BaseLongEntity {

    @Enumerated(EnumType.STRING)
    @NotNull
    private ContentActionEnum contentAction;

    private String taskId;

    private String content;

    private ContentOperationTypeEnum operationType;

    private Integer origin;

    private String enterpriseCode;

    private Long enterpriseId;

    private Integer status;

    @Transient
    private ContentPushTypeEnum pushType;

    @Transient
    private String md5;

    /**
     *  接收内容url字符串数据
     */
    @Transient
    private List<String> contents;
}

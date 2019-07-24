package com.wwyl.swan.modules.content.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 预取结果dto<br/>
 * Date：2018-4-25 13:26:15<br/>
 * Author：sh.zhang<br/>
 * */
@Getter
@Setter
public class ContentResultDto {
    private String content;
    private String md5;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date contentTime;
    private Double fitRate;
    private Short operationType;
    private Short status;
    private String origin;
    private String contentAction;
    @JSONField(serialize = false)
    private Long successAmount;
    @JSONField(serialize = false)
    private Integer serverAmount;
    private String taskId;
}

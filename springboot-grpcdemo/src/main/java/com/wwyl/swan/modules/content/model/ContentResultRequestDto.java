package com.wwyl.swan.modules.content.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 内容结果请求dto<br/>
 * Date：2018-4-25 13:26:15<br/>
 * Author：sh.zhang<br/>
 * */
@Getter
@Setter
public class ContentResultRequestDto {
    private String taskId;
    @JSONField(format = "yyyy-MM-dd")
    private Date startTime;
    @JSONField(format = "yyyy-MM-dd")
    private Date endTime;
    @JSONField(format = "yyyy-MM-dd")
    private Date contentDate;
    private String url;
    private String contentAction;
    /**
     * 企业码
     * */
    private String ecode;
    /**
     * 任务来源
     * */
    private String origin;
    /**
     * 预取状态
     * */
    private String status;

    private Integer offset;
    private Integer pageSize;
    private Integer currPage;
}

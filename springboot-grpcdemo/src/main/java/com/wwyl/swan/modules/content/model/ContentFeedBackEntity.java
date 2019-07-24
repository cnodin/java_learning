package com.wwyl.swan.modules.content.model;

import com.baomidou.mybatisplus.annotations.TableName;
import com.wwyl.lark.core.entity.SuperEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
@TableName("content_feedback")
public class ContentFeedBackEntity extends SuperEntity<ContentFeedBackEntity> {

    private String url;

    private String md5;

    private  String ip;

    private String deviceId;

    private String message;

    private String taskId;

    private Short status;

    private Date createdDate;
}

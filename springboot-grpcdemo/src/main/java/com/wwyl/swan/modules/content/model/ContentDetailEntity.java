package com.wwyl.swan.modules.content.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.wwyl.lark.core.entity.SuperEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.text.StringEscapeUtils;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Date;
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
@TableName("content_detail")
public class ContentDetailEntity extends SuperEntity<ContentDetailEntity> {

    private Integer serverNumber;
    private String customerDomain;
    private String secondDomain;
    private String firstDomain;
    private String md5;
    private String contentRange;
    @NotNull
    private String contentUrl;
    private Long contentId;
    private Short status;
    private String message;

    private String pushType;

    private Date createdDate;
    @TableField(exist = false)
    @Transient
    private List<String> deviceIds;

    public void setContentUrl(String contentUrl) {
        this.contentUrl = StringEscapeUtils.unescapeHtml4(contentUrl);
    }
}

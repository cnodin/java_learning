package com.wwyl.swan.modules.content.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.wwyl.lark.core.entity.SuperEntity;
import lombok.Getter;
import lombok.Setter;


/**
 * 内容操作关联的服务器列表
 *
 * @author sh.zhang
 * @date 2018-08-28 10:29:13
 */
@Getter
@Setter
@TableName("content_server")
public class ContentServerEntity extends SuperEntity<ContentServerEntity> {

    private static final long serialVersionUID=1L;

    /**
     * 内容详细id
     */
    @TableField("content_detail_id")
    private Long contentDetailId;

    /**
     * 机器号
     */
    @TableField("device_id")
    private String deviceId;

}
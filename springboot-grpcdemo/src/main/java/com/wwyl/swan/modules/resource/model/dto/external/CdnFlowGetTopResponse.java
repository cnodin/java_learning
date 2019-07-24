package com.wwyl.swan.modules.resource.model.dto.external;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yongming.su
 * Date: 4/5/2018
 * Time: 12:49
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Getter
@Setter
public class CdnFlowGetTopResponse {

    @JSONField(ordinal = 0)
    private String result;

    @JSONField(ordinal = 1)
    private List<UrlTopDTO> data;
}
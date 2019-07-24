package com.wwyl.swan.modules.content.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 是否需要发送二级域名的目录给kiwi
 */
@Getter
@Setter
public class KiwiContentSecondDomain{

    private static final long serialVersionUID = 1L;

    private String secondDomain;

    private Boolean isSend;
}

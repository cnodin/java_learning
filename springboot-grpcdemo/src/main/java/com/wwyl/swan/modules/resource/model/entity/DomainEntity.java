package com.wwyl.swan.modules.resource.model.entity;

import com.wwyl.lark.core.entity.BaseLongEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 30/11/2017
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 * Description: 域名实体
 */
@Getter
@Setter
@Table(name="domain")
@Entity
public class DomainEntity extends BaseLongEntity{
    private static final long serialVersionUID = 1L;

    private String name;

    private String icp;

    private Long dnsMapId;
    private Boolean enabled;

    private String remark;

    @Transient
    private String ename;	// 客户名称
    @Transient
    private String appname;	// 应用名称


    private Long enterpriseId;


    private Long applicationTypeId;

    private String originIpDomain;  // 源ip或域名

    @Transient
    private String lastModifier; //  最近一次修改人

}

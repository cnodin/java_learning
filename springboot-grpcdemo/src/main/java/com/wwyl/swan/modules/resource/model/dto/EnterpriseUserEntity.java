package com.wwyl.swan.modules.resource.model.dto;

import com.wwyl.lark.core.entity.SuperFullEntity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;


/**
 * 企业用户
 *
 * @author lfy
 * @date 2018-07-16 20:05:24
 */
@Getter
@Setter
public class EnterpriseUserEntity extends SuperFullEntity<EnterpriseUserEntity>  {


    private static final long serialVersionUID=1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 分公司名称
     */
    private String name;

    /**
     * 分公司地址
     */
    private String address;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 账号类型 0:管理员 1:其他
     */
    private Integer type;

    /**
     * 状态  0：挂起 1：正常
     */
    private Integer enability;

    /**
     * 锁定 1: 已锁定 0: 未锁定
     */
    private Integer locked;

    /**
     * 登录失败次数，默认为0
     */
    private Integer failtimes;

    /**
     * jwt私钥
     */
    private String jwtSecret;

    /**
     * 关联enterprise主键id
     */
    private Long enterpriseId;

    /**
     * 密码是否过期 0-未过期 1-已过期
     */
    private Integer passwordexpired;

    /**
     * 密码最后修改时间
     */
    private Date lastPasswordResetTime;

    /**
     * 登录输入账号密码错误次数
     */
    private Integer loginErrorTime;

    private Long pid;
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getEnability() {
        return enability;
    }

    public void setEnability(Integer enability) {
        this.enability = enability;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public Integer getFailtimes() {
        return failtimes;
    }

    public void setFailtimes(Integer failtimes) {
        this.failtimes = failtimes;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getPasswordexpired() {
        return passwordexpired;
    }

    public void setPasswordexpired(Integer passwordexpired) {
        this.passwordexpired = passwordexpired;
    }

    public Timestamp getLastPasswordResetTime() {
        if(this.lastPasswordResetTime==null){
            return null;
        }
        return new Timestamp(this.lastPasswordResetTime.getTime());
    }

    public void setLastPasswordResetTime(Date lastPasswordResetTime) {
        this.lastPasswordResetTime = lastPasswordResetTime;
    }

    public Integer getLoginErrorTime() {
        return loginErrorTime;
    }

    public void setLoginErrorTime(Integer loginErrorTime) {
        this.loginErrorTime = loginErrorTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
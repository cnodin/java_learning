package com.wwyl.swan.common.code;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 04/12/2017
 * Time: 09:13
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public enum SwanReturnCode {

    /**
     * manifest文件不存在
     */
    PUPPET_TEMPLATE_IS_NOT_EXISTS("060901"),

    /**
     * 生成Puppet失败
     */
    PUPPET_GENERATE_FAILURE("060902"),

    /**
     * 域名不属于当前企业用户
     */
    DOMAIN_NOT_BELONG("060101"),

    /**
     * 异常域名
     */
    EXCEPTION_DOMAINS("060102"),

    /**
     * 虚拟机操作异常
     */
    VIRTUAL_MACHINE_OPERA_EXCEPTION("060201"),

    /**
     * 获取网络流量异常
     */
    GET_VIRTUAL_MACHINE_NET_FLOW_FAILURE("060202"),

    /**
     * 虚拟机ID不属于当前企业
     */
    VIRTUAL_MACHINE_ID_NOT_BELONG_TO("060203"),

    /**
     * 不存在的类型
     */
    VIRTUAL_TYPE_NOT_FIND("060204"),

    /**
     * 客户域名不存在
     */
    DOMAIN_NOT_EXISTS("060205"),

    /**
     * 一次预取推送内容上限
     */
    CONTENT_UPER_LIMIT("060206"),

    /**
     * 上传文件为空
     */
    UPLOAD_FILE_FAIL("060207"),

    /**
     * 证书未生效
     */
    Certificate_NotYet_Valid("060208"),

    /**
     * 证书过期
     */
    Certificate_Expired("060209"),
    /**
     * 文件类型错误
     */
    SSL_FILE_TYPE_ERROR("060230"),
    /**
     * 企业证书信息表新增或插入失败
     */
    SET_ENTERPRISE_SSL_CERT_FAIL( "060231" ),
    /**
     * 企业证书信息详情表新增或插入失败
     */
    SET_ENTERPRISE_SSL_CERT_DETAIL_FAIL( "060232"),
    /**
     * 当前登陆客户非企业用户
     */
    IS_NOT_ENTERPRISE_USER( "060233"),

    /**
     * 请求证书资源非当前客户所有
     */
    IS_NOT_SSL_CERT_OWNER( "060234"),
    /**
     * 分页条数不能超过1000条
     * */
    PAGESIZE_UPPER_LIMIT("060235"),

    /**
     * 带宽明细单次域名个数限制
     * */
    BANDWIDTH_DOWNLOAD_LIMIT_DOMAIN("060236"),

    /**
     * 带宽明细单次下载时间跨度限制
     */
    BANDWIDTH_DOWNLOAD_LIMIT_TIME( "060237"),

    /**
     * 暂无流量信息
     */
    NOT_FLOW_INFO( "060238"),

    /**
     * 没有匹配到产品线的流量接口
     */
    NOT_MATCH_FLOW_INTERFACE( "060239"),

    /**
     * 获取外部接口数据超时
     */
    INTERFACE_TIMEOUT( "060240"),

    /**
     * 外部接口无法连接
     */
    INTERFACE_CANNOT_CONNECT( "060241"),

    /**
     * 外部接口调用异常
     */
    INTERFACE_EXCEPTION( "060242"),

    /**
     * 外部接口未授权，请检查接口是否正确
     */
    INTERFACE_NOT_AUTHORIZED( "060243"),

    /**
     * 操作失败，请稍后重试
     */
    SHELL_INVOKE_FAIL( "060244"),

    /**
     * 请先关闭虚拟机
     */
    SHUT_DOWN_FIRST( "060245"),

    /**
     * 快照生成失败，请重试或联系技术支持
     */
    SNAPSHOT_CREATE_FAIL( "060246"),

    /**
     * 创建的快照已达上限：{0}
     */
    MORE_THAN_MAX( "060247"),

    /**
     * 操作过于频繁，请稍后重试
     */
    OPERATE_TOO_MUCH( "060248"),

    /**
     * 您的虚拟机系统不支持生成快照，请联系技术支持
     */
    OS_NOT_SUPPORT_CREATE( "060249"),

    /**
     * 接口调用被中断
     */
    INTERFACE_INTERRUPT( "060250")
    ;

    private String code;

    private SwanReturnCode(String code){
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
    @Override
    public String toString() {
        return this.code;
    }


}

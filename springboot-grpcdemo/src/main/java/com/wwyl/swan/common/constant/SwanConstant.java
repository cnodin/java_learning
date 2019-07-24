package com.wwyl.swan.common.constant;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 2018/6/30
 * Time: 21:21
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public final class SwanConstant {

    /**
     * 内容URL分隔字符串
     */
    public static String CONTENT_URL_SPLIT_STRING = "\r\n";

    /**
     * 数据url列表
     */
    public final static String URL_LIST = "list";

    /**
     * 数据url总览
     */
    public final static String URL_TOTAL = "total";

    /**
     * 虚拟机监控查询项
     */
    public final static String VM_MONITOR_ITMES = "network,cpu,memory,disk";

    /**
     * 虚拟机机器类型
     */
    public final static String VM_HOST_TYPE = "kvm";

    /**
     * 异常告警名称
     */
    public final static String ALARM_NAME = "APP_Swan_Web_Exception";

    /**
     * 证书到期告警名称
     */
    public final static String SSLCERT_ALARM_NAME = "APP_certificate_warn";

    /**
     * 证书邮件锁名称
     */
    public final static String SSLCERT_LOCK_NAME = "Swan:SslCert:Email";
    /**
     * 证书告警锁名称
     */
    public final static String SSLCERT_ALARM_LOCK_NAME = "Swan:SslCert:Alarm";

    /**
     * 证书邮件锁时间
     */
    public final static Long SSLCERT_LOCK_TIME = Long.valueOf(60);
}

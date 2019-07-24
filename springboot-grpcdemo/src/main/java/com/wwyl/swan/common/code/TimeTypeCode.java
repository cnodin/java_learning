package com.wwyl.swan.common.code;

/**
 * Created with IntelliJ IDEA.
 * User: miyitang
 * Date: 23/01/2018
 * Time: 09:13
 * Description: mongodb数据时间粒度对应集合
 */
public enum TimeTypeCode {

    TIME_TYPE_LOG_5M("log_prvn_isp_5m"),

    TIME_TYPE_LOG_20M("log_prvn_isp_20m"),

    TIME_TYPE_LOG_1H("log_prvn_isp_1h"),

    TIME_TYPE_LOG_4H("log_prvn_isp_4h"),

    TIME_TYPE_LOG_1D("log_prvn_isp_1d"),

    TIME_TYPE_TRANSFER_5M("cdn_tf_5m"),

    TIME_TYPE_TRANSFER_20M("cdn_tf_20m"),

    TIME_TYPE_TRANSFER_1H("cdn_tf_1h"),

    TIME_TYPE_TRANSFER_4H("cdn_tf_4h"),

    TIME_TYPE_TRANSFER_1D("cdn_tf_1d"),

    TIME_TYPE_LOG("log"),

    TIME_TYPE_TRANSFER("transfer")
    ;

    private String collectionName;

    private TimeTypeCode(String collectionName){
        this.collectionName = collectionName;
    }

    @Override
    public String toString() {
        return this.collectionName;
    }
}

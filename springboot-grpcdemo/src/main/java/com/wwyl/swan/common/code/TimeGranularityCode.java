package com.wwyl.swan.common.code;

/**
 * Created with IntelliJ IDEA.
 * User: miyitang
 * Date: 23/01/2018
 * Time: 09:13
 * Description: mongodb数据时间粒度对应集合
 */
public enum TimeGranularityCode {

    FIVE_MIN("5m"),

    TWENTY_MIN("20m"),

    ONE_HOUR("1h"),

    FOUR_HOUR("4h"),

    ONE_DAY("1d")
    ;

    private String timeGranularity;

    private TimeGranularityCode(String collectionName){
        this.timeGranularity = collectionName;
    }

    @Override
    public String toString() {
        return this.timeGranularity;
    }
}

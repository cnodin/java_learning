package com.wwyl.swan.modules.resource.model.dto;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 25/11/2017
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Getter
public enum FlowSpanEnum {
    FiveMinutes("5m"),
    TwentyMinutes("20m"),
    OneHour("1h"),
    FourHours("4h"),
    OneDay("1d");

    private String code;

    private FlowSpanEnum(String code){
        this.code = code;
    }


    @Override
    public String toString() {
        return this.code;
    }

    public static FlowSpanEnum of(String value){
        switch (value) {
            case "5m": return FiveMinutes;
            case "20m": return TwentyMinutes;
            case "1h": return OneHour;
            case "4h": return FourHours;
            case "1d": return OneDay;
            default: return FiveMinutes;
        }
    }
}

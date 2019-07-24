package com.wwyl.swan.modules.content.model;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import lombok.Getter;

import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 04/12/2017
 * Time: 15:03
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Getter
public enum ContentActionEnum {

    /**
     * 推送
     */
    PUSH("push"),

    /**
     * 预取
     */
    FETCH("fetch") ,

    /**
     * md5校验
     */
    MD5_FETCH("md5_fetch"),

    /**
     * md5检测
     */
    MD5_ONLY("md5_only");

    private String value;

    private ContentActionEnum(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static class EnumDeserializer implements ObjectDeserializer {

        @SuppressWarnings("unchecked")
        @Override
        public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
            String strValue = parser.parse(fieldName).toString();
            if (strValue.equals("push")) {
                return (T) ContentActionEnum.PUSH;
            }else if (strValue.equals("fetch")) {
                return (T) ContentActionEnum.FETCH;
            }else if (strValue.equals("md5_fetch")) {
                return (T) ContentActionEnum.MD5_FETCH;
            }else if (strValue.equals("md5_only")) {
                return (T) ContentActionEnum.MD5_ONLY;
            }
            throw new IllegalStateException();
        }

        @Override
        public int getFastMatchToken() {
            return JSONToken.LITERAL_STRING;
        }
    }

}

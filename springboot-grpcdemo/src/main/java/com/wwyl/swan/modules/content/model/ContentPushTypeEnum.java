package com.wwyl.swan.modules.content.model;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import lombok.Getter;

import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 02/12/2017
 * Time: 11:55
 * To change this template use File | Settings | File Templates.
 * Description: 内容推送类型
 */
@Getter
public enum ContentPushTypeEnum {

    /**
     * 单个URL资源
     */
    URL("0"),

    /**
     * 目录
     */
    DIR("1");

    private String value;

    private ContentPushTypeEnum(String value){
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
            if (strValue.equals("0")) {
                return (T) ContentPushTypeEnum.URL;
            }else if (strValue.equals("1")) {
                return (T) ContentPushTypeEnum.DIR;
            }
            throw new IllegalStateException();
        }

        @Override
        public int getFastMatchToken() {
            return JSONToken.LITERAL_STRING;
        }
    }

}

package com.wwyl.swan.common.convert;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.wwyl.swan.modules.content.model.ContentActionEnum;
import com.wwyl.swan.modules.content.model.ContentOperationTypeEnum;
import com.wwyl.swan.modules.content.model.ContentPushTypeEnum;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 04/12/2017
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class FastJsonHttpMessageConverter4Ex extends FastJsonHttpMessageConverter4 {

    public FastJsonHttpMessageConverter4Ex(){
        super();
        ParserConfig.getGlobalInstance().putDeserializer(ContentPushTypeEnum.class,
                new ContentPushTypeEnum.EnumDeserializer());
        ParserConfig.getGlobalInstance().putDeserializer(ContentOperationTypeEnum.class,
                new ContentOperationTypeEnum.EnumDeserializer());
        ParserConfig.getGlobalInstance().putDeserializer(ContentActionEnum.class,
                new ContentActionEnum.EnumDeserializer());
    }

}

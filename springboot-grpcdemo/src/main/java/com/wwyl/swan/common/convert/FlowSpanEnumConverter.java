package com.wwyl.swan.common.convert;

import com.wwyl.swan.modules.resource.model.dto.FlowSpanEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 25/11/2017
 * Time: 16:13
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class FlowSpanEnumConverter implements Converter<String, FlowSpanEnum> {
    @Override
    public FlowSpanEnum convert(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        return FlowSpanEnum.of(source);
    }
}

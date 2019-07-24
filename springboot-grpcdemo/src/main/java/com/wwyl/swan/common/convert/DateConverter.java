package com.wwyl.swan.common.convert;

import com.wwyl.lark.util.time.DateUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 25/11/2017
 * Time: 17:14
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }

        return DateUtils.parseDate(source, "yyyy-MM-dd");
    }
}

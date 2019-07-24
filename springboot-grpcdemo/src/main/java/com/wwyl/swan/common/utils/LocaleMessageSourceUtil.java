package com.wwyl.swan.common.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 22/11/2017
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Component
public class LocaleMessageSourceUtil {

    @Resource
    private MessageSource messageSource;

    public String getMessage(String code) {
        return this.getMessage(code, null);
    }

    /**
     *
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @return
     */
    public String getMessage(String code, Object[] args){
        return getMessage(code, args, "");
    }

    /**
     *
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @param defaultMessage : 没有设置key的时候的默认值.
     * @return
     */
    public String getMessage(String code,Object[] args,String defaultMessage){
        //这里使用比较方便的方法，不依赖request.
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }

    public String getMessageUseRequest(String code, HttpServletRequest request){
        Locale locale = RequestContextUtils.getLocale(request);
        return this.getMessageUseReqeust(code, null, null, request);
    }

    public String getMessageUseRequest(String code,Object[] args, HttpServletRequest request){
        return this.getMessageUseRequest(code, args, request);
    }

    /**
     * 根据HttpServletRequest来确定locale，并获取资源
     *
     * @param code
     * @param args
     * @param defaultMessage
     * @param request
     * @return
     */
    public String getMessageUseReqeust(String code,Object[] args,String defaultMessage, HttpServletRequest request){
        Locale locale = RequestContextUtils.getLocale(request);
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }
}

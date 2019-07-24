package com.wwyl.swan.common.advice;

import com.alibaba.fastjson.JSON;
import com.wwyl.lark.core.model.ResultBean;
import com.wwyl.swan.common.utils.LocaleMessageSourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 06/12/2017
 * Time: 13:19
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@ControllerAdvice
@Slf4j
public class CustomizeResponseBodyAdvice implements ResponseBodyAdvice<ResultBean> {

    @Autowired
    private LocaleMessageSourceUtil messageSourceUtil;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

        return returnType.getMethod().getReturnType()
                    .equals(ResultBean.class);
    }

    @Override
    public ResultBean beforeBodyWrite(ResultBean body,
                                      MethodParameter returnType,
                                      MediaType selectedContentType,
                                      Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                      ServerHttpRequest request,
                                      ServerHttpResponse response) {

        if (body != null &&
                !StringUtils.isEmpty(body.getCode())) {
            if (StringUtils.isEmpty(body.getMsg())) {
                body.setMsg(messageSourceUtil.getMessage(body.getCode()));
            } else {
                body.setMsg(messageSourceUtil.getMessage(body.getCode(),
                        new Object[]{body.getMsg()}));
            }
        }
        log.info("url:{},response message: {}",request.getURI(), JSON.toJSONString(body, true));
        return body;
    }
}

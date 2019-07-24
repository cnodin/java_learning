package com.wwyl.swan.common.exception.handler;

import com.wwyl.lark.core.model.ResultBean;
import com.wwyl.swan.common.code.SwanReturnCode;
import com.wwyl.swan.common.utils.LocaleMessageSourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 06/12/2017
 * Time: 14:40
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@ControllerAdvice
@Slf4j
@Order(2)
public class RestClientExceptionHandler {

    @Autowired
    private LocaleMessageSourceUtil messageSourceUtil;

    @ExceptionHandler(value = RestClientException.class)
    @ResponseBody
    public ResponseEntity handleBaseRuntimeException(RestClientException e){
        log.error("outer interface error:", e);
        ResultBean result = new ResultBean();
        result.setCode(SwanReturnCode.INTERFACE_EXCEPTION.getCode());

        if (e instanceof ResourceAccessException) {
            result.setCode(SwanReturnCode.INTERFACE_TIMEOUT.getCode());

        }else if (e instanceof HttpServerErrorException) {
            result.setCode(SwanReturnCode.INTERFACE_CANNOT_CONNECT.getCode());

        }else if (e instanceof HttpClientErrorException) {
            result.setCode(SwanReturnCode.INTERFACE_NOT_AUTHORIZED.getCode());
        }

        result.setMsg(messageSourceUtil.getMessage(result.getCode(), new Object[]{e.getMessage()}));
        return new ResponseEntity(result, HttpStatus.OK);
    }
}

package com.wwyl.swan.common.exception.handler;

import com.wwyl.lark.core.model.ResultBean;
import com.wwyl.lark.util.exception.BaseRuntimeException;
import com.wwyl.swan.common.utils.LocaleMessageSourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Order(1)
public class BaseRuntimeExceptionHandler {

    @Autowired
    private LocaleMessageSourceUtil messageSourceUtil;

    @ExceptionHandler(value = BaseRuntimeException.class)
    @ResponseBody
    public ResponseEntity handleBaseRuntimeException(BaseRuntimeException e){
        ResultBean result = ResultBean.UncaughtedError();
        result.setCode(e.getErrorCode());
        result.setMsg(messageSourceUtil.getMessage(result.getCode(), new Object[]{e.getMessage()}));

        return new ResponseEntity(result, HttpStatus.OK);
    }
}

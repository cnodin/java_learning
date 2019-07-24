package com.wwyl.swan.common.exception.handler;

import com.wwyl.lark.core.code.CommonReturnCode;
import com.wwyl.lark.core.model.ResultBean;
import com.wwyl.lark.model.MonitorExceptionDTO;
import com.wwyl.swan.common.constant.SwanConstant;
import com.wwyl.swan.common.utils.LocaleMessageSourceUtil;
import com.wwyl.swan.common.utils.MonitorExcepitonHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 22/11/2017
 * Time: 14:02
 * To change this template use File | Settings | File Templates.
 * Description: Web全局异常处理器
 */
@ControllerAdvice
@Slf4j
@Order(100)
public class WebGlobalExceptionHandler {

    @Autowired
    private LocaleMessageSourceUtil messageSourceUtil;
    @Autowired
    private Tracer tracer;
    @Autowired
    private MonitorExcepitonHelper monitorHelper;

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<ResultBean> handle405Exception(HttpServletRequest request,
                                                         HttpServletResponse response,
                                                         HttpRequestMethodNotSupportedException e){

        String url = request.getRequestURL().toString();
        log.info("entry 405 exception, {}, {}", e, url);

        ResultBean<String> resultBean = new ResultBean<String>(CommonReturnCode.RESOURCE_IS_NOT_EXISTS.getCode(), e);
        ResponseEntity<ResultBean> responseEntity = new ResponseEntity<ResultBean>(resultBean, HttpStatus.METHOD_NOT_ALLOWED);
        return responseEntity;
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ResponseEntity bindExceptionHandler(HttpServletRequest request, BindException e) {
        ResultBean<List> result = ResultBean.UncaughtedError();
        List<ObjectError> errors =  e.getAllErrors();
        List<String> errorMessages = new ArrayList<>();
        errors.stream().forEach(oe -> {
            if (oe instanceof FieldError) {
                FieldError fe = (FieldError)oe;
                errorMessages.add(String.format("%s:%s", fe.getField(), fe.getDefaultMessage()));
            }
        });
        result.setCode(CommonReturnCode.REQUEST_DATA_INVALID.getCode());
        result.setData(errorMessages);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity uncaughtedExceptionHandler(HttpServletRequest request, Exception e){
        log.error("uncaughted exception: {} ",e);
        Span span = tracer.getCurrentSpan();
        String url = request.getRequestURL().toString();
        String tracerId = span.traceIdString();
        String message = e.getStackTrace()[0].toString();
        String exceptionType = e.toString();
        String alarmName = SwanConstant.ALARM_NAME;
        MonitorExceptionDTO monitorExceptionDTO = new MonitorExceptionDTO(url,tracerId,message,exceptionType);
        monitorHelper.sendAsyncExceptionAlarm(alarmName,monitorExceptionDTO);
        ResultBean result = ResultBean.UncaughtedError();
        result.setCode(CommonReturnCode.UNCAUGHTED_ERROR.getCode());
        result.setMsg(messageSourceUtil.getMessage(result.getCode(), new Object[]{e.getMessage()}));
        return new ResponseEntity(result, HttpStatus.OK);
    }
}

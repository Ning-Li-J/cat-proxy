package org.xupt.cat.proxy.api.controller.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.enums.ErrorCode;
import org.xupt.cat.proxy.api.utils.ResponseUtil;

/**
 * @author lining
 * @data 2020-04-08 上午11:34
 */
@Slf4j
@ControllerAdvice
public class ApiControllerAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResponse handleParamException(MethodArgumentNotValidException e) {
        log.error("request param error! ", e);
        return ResponseUtil.buildFailResponce(ErrorCode.REQUEST_PARAM_ERROR);

    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseResponse handleRuntimeException(RuntimeException runtimeException) {
        log.error(runtimeException.getMessage(), runtimeException);
        return ResponseUtil.buildFailResponce(ErrorCode.SYSTEM_ERROR);
    }
}

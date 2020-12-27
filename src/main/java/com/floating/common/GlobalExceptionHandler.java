package com.floating.common;

/**
 * @author Mr_Fei
 * @description
 * @date 2020-12-21 23:45
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 捕捉shiro的异常
     *
     * @param e exception
     * @return com.floating.common.ResponseData
     * @author Mr_Fei
     * @date 2020/12/21 23:48
     * @description handle401 捕捉shiro的异常
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ResponseData handle401(ShiroException e) {
        return new ResponseData<>(ResponseData.SYS_ERROR_CODE, ResponseData.FROM_USER_NO_ACCESS, e.getMessage());
    }

    /**
     * 断言异常处理
     *
     * @param e exception
     * @return com.floating.common.ResponseData
     * @author Mr_Fei
     * @date 2020/12/21 23:48
     * @description handler 处理Assert的异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseData handler(IllegalArgumentException e) {
        log.error("Assert异常:-------------->{}", e.getMessage());
        return ResponseData.failed(e.getMessage());
    }

    /**
     * 实体校验异常
     *
     * @param e 校验错误异常处理
     * @return com.floating.common.ResponseData
     * @author Mr_Fei
     * @date 2020/12/21 23:47
     * @description handler 校验错误异常处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseData handler(MethodArgumentNotValidException e) {
        log.error("实体校验异常:-------------->{}", e);
        BindingResult bindingResult = e.getBindingResult();
        Optional<ObjectError> first = bindingResult.getAllErrors().stream().findFirst();
        ObjectError objectError = first.orElseGet(() -> new ObjectError(e.getClass().toString(), e.getMessage()));
        return ResponseData.failed(objectError.getDefaultMessage());

    }

    /**
     * 运行时异常捕获
     *
     * @param e 运行时异常
     * @return com.floating.common.ResponseData
     * @author Mr_Fei
     * @date 2020/12/22 21:51
     * @description handler
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseData handler(RuntimeException e) {
        log.error("运行时异常:-------------->{}", e);
        return ResponseData.failed(e.getMessage());
    }
}


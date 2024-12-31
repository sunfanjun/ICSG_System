package com.zjut.springbootprj.exception;

import com.zjut.springbootprj.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 如果抛出的的是ServiceException，则调用该方法
     * @param se 业务异常
     * @return Result
     */
    @ExceptionHandler(com.zjut.springbootprj.exception.ServiceException.class)
    @ResponseBody
    public Result handle(com.zjut.springbootprj.exception.ServiceException se){
        return Result.error(se.getCode(),se.getMessage());
    }

}

package com.okay.family.common;


import com.okay.family.fun.base.bean.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandle {

    public static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultErrorHandler(HttpServletRequest req, Exception e) {
        /**参数错误*/
        if (e instanceof MethodArgumentNotValidException) {
            String defaultMessage = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();
            logger.error("参数异常:", defaultMessage);
            return Result.build(CommonCode.PARAMS_ERROR.getCode(), defaultMessage);
        }

        return Result.fail(e.getMessage());
    }


}

package com.okay.family.common.wapper;


import com.okay.family.common.code.CommonCode;
import com.okay.family.common.code.DataBaseCode;
import com.okay.family.fun.base.bean.Result;
import org.apache.ibatis.exceptions.IbatisException;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CommonExceptionHandler {

    public static Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultErrorHandler(HttpServletRequest req, Exception e) {
        /**参数错误*/
        if (e instanceof MethodArgumentNotValidException) {
            String defaultMessage = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();
            logger.error("参数异常:{}", defaultMessage);
            return Result.fail(CommonCode.PARAMS_ERROR.getCode(), defaultMessage);
        }
        if (e instanceof DuplicateKeyException) {
            String message = e.getMessage();
            logger.error("唯一性校验异常", e);
            return Result.fail(DataBaseCode.ONLY_KEY_FAIL);
        }
        if (e instanceof MyBatisSystemException) {
            String message = e.getMessage();
            logger.error("mybatis系统异常", e);
            return Result.fail(DataBaseCode.MYBATIS_FAIL);
        }
        if (e instanceof IbatisException) {
            String message = e.getMessage();
            logger.error("mybatis配置错误", e);
            return Result.fail(DataBaseCode.MYBATIS_CONFIG_ERROR);
        }
        if (e instanceof NonTransientDataAccessException) {
            String message = e.getMessage();
            logger.error("数据库格式错误!", e);
            return Result.fail(DataBaseCode.MYBATIS_CONFIG_ERROR);
        }
        if (e instanceof MissingServletRequestParameterException) {
            String message = e.getMessage();
            logger.error("数据格式错误!", e);
            return Result.fail(CommonCode.PARAMS_ERROR);
        }
        if (e instanceof BindException) {
            String message = e.getMessage();
            logger.error("validation参数校验失败!", e);
            return Result.fail(CommonCode.BIND_ERROR);
        }
        logger.warn("未记录异常类", e);
        return Result.fail(e.getMessage());
    }


}

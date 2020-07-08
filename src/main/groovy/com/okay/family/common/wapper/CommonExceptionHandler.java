package com.okay.family.common.wapper;


import com.okay.family.common.code.CommonCode;
import com.okay.family.common.code.DataBaseCode;
import com.okay.family.fun.base.bean.Result;
import com.okay.family.fun.base.exception.FailException;
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
            logger.error("参数异常", e);
            return Result.fail(CommonCode.PARAMS_ERROR,e.getMessage());
        }
        if (e instanceof DuplicateKeyException) {
            logger.error("唯一性校验异常", e);
            return Result.fail(DataBaseCode.ONLY_KEY_FAIL,e.getMessage());
        }
        if (e instanceof MyBatisSystemException) {
            logger.error("mybatis系统异常", e);
            return Result.fail(DataBaseCode.MYBATIS_FAIL,e.getMessage());
        }
        if (e instanceof IbatisException) {
            logger.error("mybatis配置错误", e);
            return Result.fail(DataBaseCode.MYBATIS_CONFIG_ERROR,e.getMessage());
        }
        if (e instanceof NonTransientDataAccessException) {
            logger.error("数据库格式错误!", e);
            return Result.fail(DataBaseCode.MYBATIS_CONFIG_ERROR,e.getMessage());
        }
        if (e instanceof MissingServletRequestParameterException) {
            logger.error("数据格式错误!", e);
            return Result.fail(CommonCode.PARAMS_ERROR,e.getMessage());
        }
        if (e instanceof BindException) {
            String message = e.getMessage();
            logger.error("validation参数校验失败!", e);
            String defaultMessage = ((BindException) e).getBindingResult().getFieldError().getDefaultMessage();
            return Result.fail(CommonCode.PARAMS_ERROR,defaultMessage);
        }
        if (e instanceof FailException) {
            String message = e.getMessage();
            logger.error("捕获自定义异常", e);
            return Result.fail(CommonCode.PARAMS_ERROR,message);
        }
        logger.warn("未记录异常类", e);
        return Result.fail(CommonCode.UNKNOW_ERROR,e.getMessage());
    }


}

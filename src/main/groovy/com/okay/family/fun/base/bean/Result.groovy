package com.okay.family.fun.base.bean

import com.alibaba.fastjson.JSONObject
import com.okay.family.common.code.CommonCode
import com.okay.family.fun.base.interfaces.ReturnCode
import com.okay.family.fun.config.Constant

/**
 * 通用的返回体
 * 配合moco框架使用
 */
class Result<T> extends AbstractBean {

    /**
     * code码
     */
    int code

    String msg

    /**
     * 返回信息
     */
    T data

    Result(int code, String msg, T data) {
        this.code = code
        this.msg = msg
        this.data = data
    }

    Result(int code, String msg) {
        this.code = code
        this.msg = msg
        this.data = "null"
    }

    def Result() {
    }
/**
 * 返回成功响应内容
 * @param data
 * @return
 */
    static <T> Result<T> success(T data) {
        new Result<>(CommonCode.SUCCESS.getCode(), CommonCode.SUCCESS.getDesc(), data)
    }

    static Result success() {
        new Result(CommonCode.SUCCESS.getCode(), CommonCode.SUCCESS.getDesc())
    }

    static Result build(ReturnCode errorCode) {
        new Result(errorCode)
    }

    static Result build(int code, String msg) {
        new Result(code, msg)
    }

    static Result build(List listData) {
        success([list: listData] as JSONObject)
    }
    
/**
 * 返回通用失败的响应内容
 * @param data
 * @return
 */
    static <T> Result<T> fail(T data) {
        new Result<T>(Constant.TEST_ERROR_CODE, data)
    }

    static Result fail(ReturnCode errorCode) {
        new Result(errorCode)
    }

    Result(ReturnCode errorCode) {
        this(errorCode.getCode(),errorCode.getDesc())
    }


}

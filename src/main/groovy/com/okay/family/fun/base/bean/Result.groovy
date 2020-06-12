package com.okay.family.fun.base.bean

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
    }
/**
 * 返回简单的响应
 * @param c
 */

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

    static <T> Result<T> success() {
        new Result<>(CommonCode.SUCCESS.getCode(), CommonCode.SUCCESS.getDesc())
    }

    static <T> Result<T> build(ReturnCode errorCode) {
        new Result(errorCode)
    }

    static <T> Result<T> build(int code, String msg) {
        new Result(code, msg)
    }

/**
 * 返回通用失败的响应内容
 * @param data
 * @return
 */
    static <T> Result<T> fail(T data) {
        new Result<T>(Constant.TEST_ERROR_CODE, data)
    }

    static <T> Result<T> fail(ReturnCode errorCode) {
        new Result<T>(errorCode)
    }

    Result(ReturnCode errorCode) {
        this.code = errorCode.getCode()
        this.msg = errorCode.getDesc()
    }


}

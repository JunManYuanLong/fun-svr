package com.okay.family.fun.base.bean

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.github.pagehelper.PageInfo
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

    private Result(int code, String msg, T data) {
        this.code = code
        this.msg = msg
        this.data = data
    }

    private Result(int code, String msg) {
        this(code, msg, "null")
    }

    private Result(ReturnCode errorCode) {
        this(errorCode.getCode(), errorCode.getDesc())
    }

    private Result() {
    }

/**
 * 返回成功响应内容
 * @param data
 * @return
 */
    static <T> Result<T> success(T data) {
        new Result(0, CommonCode.SUCCESS.getDesc(), data)
    }

    static Result success(PageInfo data) {
        JSONObject res = new JSONObject();
        res.put("pageNum", data.getPageNum());
        res.put("list", data.getList());
        res.put("total", data.getTotal());
        new Result(CommonCode.SUCCESS.getCode(), CommonCode.SUCCESS.getDesc(), res)
    }

    static Result success() {
        new Result(CommonCode.SUCCESS)
    }

    static Result success(JSONArray listData) {
        success([list: listData] as JSONObject)
    }

    static Result success(List listData) {
        success([list: listData] as JSONObject)
    }


/**
 * 返回通用失败的响应内容
 * @param data
 * @return
 */
    static <T> Result<T> fail(T data) {
        new Result<T>(Constant.TEST_ERROR_CODE, data.toString())
    }

    static Result fail(ReturnCode errorCode) {
        new Result(errorCode)
    }

    static Result fail(int code, String msg) {
        new Result(code, msg)
    }

}

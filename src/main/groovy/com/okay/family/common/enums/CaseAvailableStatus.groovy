package com.okay.family.common.enums

import com.alibaba.fastjson.JSONObject

import java.util.stream.Collectors

/**
 * 用例状态和运行状态记录表,user_error和un_know只存在于运行状态.
 */
enum CaseAvailableStatus {

    OK(1, "可用"),
    NO(2, "不可用"),
    USER_ERROR(3, "用户错误"),
    UN_KNOW(4, "无刻描述")

    int code

    String desc

    CaseAvailableStatus(int code, String desc) {
        this.code = code
        this.desc = desc
    }

    static JSONObject getAll() {
        CaseAvailableStatus.values().stream().collect(Collectors.toMap({x -> x.getCode() as String}, {x -> x.getDesc()})) as JSONObject
    }

}

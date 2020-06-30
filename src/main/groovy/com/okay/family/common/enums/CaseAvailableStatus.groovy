package com.okay.family.common.enums

import com.alibaba.fastjson.JSONArray
import com.okay.family.fun.frame.SourceCode
/**
 * 用例状态和运行状态记录表,user_error和un_know只存在于运行状态.
 */
enum CaseAvailableStatus {

    OK(1, "可用"),
    NO(2, "不可用"),
    USER_ERROR(3, "用户错误"),
    UN_KNOW(4, "无法描述")

    int code

    String desc

    CaseAvailableStatus(int code, String desc) {
        this.code = code
        this.desc = desc
    }

    static JSONArray getAll() {
        JSONArray result = new JSONArray()
        result << SourceCode.getJson("id=" + OK.getCode(), "name=" + OK.getDesc())
        result << SourceCode.getJson("id=" + NO.getCode(), "name=" + NO.getDesc())
        result
    }

}

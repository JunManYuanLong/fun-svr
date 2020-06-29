package com.okay.family.common.enums

import com.alibaba.fastjson.JSONArray

enum DelStatus {

    NOT_YET(1, "还未删除,默认状态"),
    ALREADY(2, "已经删除")

    int code

    String desc

    DelStatus(int code, String desc) {
        this.code = code
        this.desc = desc
    }

    static JSONArray getAll() {
        JSONArray result = new JSONArray()
        DelStatus.values().each {x -> result << SourceCode.getJson("id=" + x.getCode(), "name=" + x.getDesc())}
        result
    }
}
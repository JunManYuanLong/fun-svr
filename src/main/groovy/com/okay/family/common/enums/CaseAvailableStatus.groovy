package com.okay.family.common.enums

import com.alibaba.fastjson.JSONObject

import java.util.stream.Collectors

enum CaseAvailableStatus {

    OK(1, "可用"),
    NO(2, "不可用")

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

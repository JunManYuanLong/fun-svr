package com.okay.family.common.enums

import com.alibaba.fastjson.JSONObject

import java.util.stream.Collectors

enum CaseLevel {


    DEAFULT(1, "默认等级"),
    HIGH(2, "高运行等级"),
    NORMAL(3, "普通运行等级"),
    LOW(4, "低运行等级")


    int code

    String desc

    CaseLevel(int code, String desc) {
        this.code = code
        this.desc = desc
    }

    static JSONObject getAll() {
        CaseLevel.values().stream().collect(Collectors.toMap({x -> x.getCode() as String}, {x -> x.getDesc()})) as JSONObject
    }

}
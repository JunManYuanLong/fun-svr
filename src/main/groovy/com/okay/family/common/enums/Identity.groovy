package com.okay.family.common.enums

import com.alibaba.fastjson.JSONObject

import java.util.stream.Collectors

enum Identity {

    STU_PAD(1, "学生pad"),
    STU_WEB(2, "学生空间"),
    TEA_PAD(3, "老师pad"),
    TEA_WEB(4, "教师空间")

    int code

    def desc

    Identity(int code, def desc) {
        this.code = code
        this.desc = desc
    }

    static JSONObject getAll() {
        Identity.values().stream().collect(Collectors.toMap({x -> x.getCode() as String}, {x -> x.getDesc()})) as JSONObject
    }

}
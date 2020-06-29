package com.okay.family.common.enums

import com.alibaba.fastjson.JSONObject

import java.util.stream.Collectors

enum PubStatus {

    ALREADY(1, "已共享"),
    NOTYET(2, "未共享")

    int code

    int desc

    PubStatus(int code, int desc) {
        this.code = code
        this.desc = desc
    }

    static JSONObject getAll() {
        PubStatus.values().stream().collect(Collectors.toMap({x -> x.getCode() as String}, {x -> x.getDesc()})) as JSONObject
    }

}
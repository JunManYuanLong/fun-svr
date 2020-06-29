package com.okay.family.common.enums

import com.alibaba.fastjson.JSONObject

import java.util.stream.Collectors

enum DelStatus {

    NOT_YET(1, "还未删除,默认状态"),
    ALREADY(2, "已经删除")

    int code

    String desc

    DelStatus(int code, String desc) {
        this.code = code
        this.desc = desc
    }

    static JSONObject getAll() {
        DelStatus.values().stream().collect(Collectors.toMap({x -> x.getCode() as String}, {x -> x.getDesc()})) as JSONObject
    }

}
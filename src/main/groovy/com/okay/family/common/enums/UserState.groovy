package com.okay.family.common.enums

import com.alibaba.fastjson.JSONObject

import java.util.stream.Collectors

enum UserState {

    DEFAULT(1, "默认状态"),
    OK(2, "可用状态"),
    NO(3, "不可用状态"),
    CANNOT(4, "无法验证状态")

     int code

     String desc

    UserState(int code, String desc) {
        this.code = code
        this.desc = desc
    }

    static JSONObject getAll() {
        UserState.values().stream().collect(Collectors.toMap({x -> x.getCode() as String}, {x -> x.getDesc()})) as JSONObject
    }

}
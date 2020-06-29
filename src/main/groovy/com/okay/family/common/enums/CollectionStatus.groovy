package com.okay.family.common.enums

import com.alibaba.fastjson.JSONObject

import java.util.stream.Collectors

enum CollectionStatus {


    DEFAULT(1, "未测试"),
    SUCCESS(2, "成功"),
    FAIL(3, "失败")

    int code

    String desc

    CollectionStatus(int code, String desc) {
        this.code = code
        this.desc = desc
    }

    static JSONObject getAll() {
        CollectionStatus.values().stream().collect(Collectors.toMap({x -> x.getCode() as String}, {x -> x.getDesc()})) as JSONObject
    }

}
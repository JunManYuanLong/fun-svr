package com.okay.family.common.enums

import com.alibaba.fastjson.JSONArray

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

    static JSONArray getAll() {
        JSONArray result = new JSONArray()
        CollectionStatus.values().each {x -> result << SourceCode.getJson("id=" + x.getCode(), "name=" + x.getDesc())}
        result
    }

}
package com.okay.family.common.enums

import com.alibaba.fastjson.JSONArray

enum PubStatus {

    ALREADY(1, "已共享"),
    NOTYET(2, "未共享")

    int code

    int desc

    PubStatus(int code, int desc) {
        this.code = code
        this.desc = desc
    }

    static JSONArray getAll() {
        JSONArray result = new JSONArray()
        PubStatus.values().each {x -> result << SourceCode.getJson("id=" + x.getCode(), "name=" + x.getDesc())}
        result
    }

}
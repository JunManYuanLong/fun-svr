package com.okay.family.common.enums

import com.alibaba.fastjson.JSONObject

import java.util.stream.Collectors

/**
 * 接口请求方式,get postform, postjson,枚举类
 */
public enum RequestType {

    GET(1, "GET"),
    POST_JSON(2, "POST-JSON"),
    POST_FORM(3, "POST-FORM");

    int code;

    String desc;

    RequestType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    static JSONObject getAll() {
        RequestType.values().stream().collect(Collectors.toMap({x -> x.getCode() as String}, {x -> x.getDesc()})) as JSONObject
    }

}

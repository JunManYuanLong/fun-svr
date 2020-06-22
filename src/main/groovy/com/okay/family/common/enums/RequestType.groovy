package com.okay.family.common.enums;

/**
 * 接口请求方式,get postform, postjson,枚举类
 */
public enum RequestType {

    GET(1, "GET"),
    POST_JSON(2, "POST_JSON"),
    POST_FORM(3, "POST_FORM");

    int code;

    String desc;

    RequestType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}

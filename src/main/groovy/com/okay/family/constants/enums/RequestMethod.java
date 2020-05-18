package com.okay.family.constants.enums;

/**
 * 接口请求方式,get postform, postjson,枚举类
 */
public enum RequestMethod {

    GET(1), POST_JSON(2), POST_FORM(3), NONE(4);

    int code;

    private RequestMethod(int code) {
        this.code = code;
    }

    public static RequestMethod getType(int i) {

        for (RequestMethod method : RequestMethod.values()) {
            if (method.code == i) return method;
        }
        return NONE;
    }

}

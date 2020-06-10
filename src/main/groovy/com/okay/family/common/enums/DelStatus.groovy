package com.okay.family.common.enums

enum DelStatus {

    NOT_YET(0, "还未删除,默认状态"),
    ALREADY(1, "已经删除")

    int code

    String desc

    DelStatus(int code, String desc) {
        this.code = code
        this.desc = desc
    }

}
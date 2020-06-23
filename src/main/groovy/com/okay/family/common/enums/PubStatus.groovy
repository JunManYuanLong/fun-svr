package com.okay.family.common.enums

enum PubStatus {

    ALREADY(1, "已共享"),
    NOTYET(2, "未共享")

    int code

    int desc

    PubStatus(int code, int desc) {
        this.code = code
        this.desc = desc
    }
}
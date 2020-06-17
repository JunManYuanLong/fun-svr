package com.okay.family.common.enums

enum CaseLevel {


    DEAFULT(1, "默认等级"),
    HIGH(2, "高运行等级"),
    NORMAL(3, "普通运行等级"),
    LOW(4, "低运行等级")


    int code

    String desc

    CaseLevel(int code, String desc) {
        this.code = code
        this.desc = desc
    }

}
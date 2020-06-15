package com.okay.family.common.enums

enum CaseLevel {


    DEAFULT(8, "最运行等级"),
    HIGH(1, "高运行等级"),
    NORMAL(2, "普通运行等级"),
    LOW(3, "低运行等级")


    int code

    String desc

    CaseLevel(int code, String desc) {
        this.code = code
        this.desc = desc
    }

}
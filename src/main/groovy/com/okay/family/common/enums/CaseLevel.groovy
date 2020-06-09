package com.okay.family.common.enums

enum CaseLevel {


    DEAFULT(0, "最运行等级"),
    HIGH(1, "高运行等级"),
    NORMAL(2, "普通运行等级"),
    LOW(3, "低运行等级")


    int level

    String desc

    CaseLevel(int level, String desc) {
        this.level = level
        this.desc = desc
    }
}
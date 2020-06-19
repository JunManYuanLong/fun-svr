package com.okay.family.common.enums

enum CaseAvailableStatus {

    OK(1, "可用"),
    NO(2, "不可用")

    int code

    String desc

    CaseAvailableStatus(int code, String desc) {
        this.code = code
        this.desc = desc
    }
}

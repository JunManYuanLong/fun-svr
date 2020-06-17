package com.okay.family.common.enums

enum CaseStatus {

    DEFAULT(1, "默认状态"),
    SUCCESS(2, "成功"),
    FAIL(3, "失败"),
    UNRUN(4, "无法运行"),
    USER_EROOR(5, "用户错误")

    int code

    String desc

    CaseStatus(int code, String desc) {
        this.code = code
        this.desc = desc
    }


}
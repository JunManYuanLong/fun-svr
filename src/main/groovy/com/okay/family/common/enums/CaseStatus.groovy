package com.okay.family.common.enums

enum CaseStatus {

    DEFAULT(0, "默认状态"),
    SUCCESS(1, "成功"),
    FAIL(2, "失败"),
    UNRUN(3, "无法运行"),
    USER_EROOR(4, "用户错误")

    private int code

    private String desc

    UserState(int code, String desc) {
        this.code = code
        this.desc = desc
    }

    int getcode() {
        code
    }

    String getDesc() {
        desc
    }

}
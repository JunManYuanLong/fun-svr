package com.okay.family.common.enums

enum UserState {

    DEFAULT(0, "默认状态"),
    OK(1, "可用状态"),
    NO(2, "不可用状态"),
    CANNOT(3, "无法验证状态")

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
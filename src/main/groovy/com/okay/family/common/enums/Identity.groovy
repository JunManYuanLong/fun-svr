package com.okay.family.common.enums

enum Identity {

    STU_PAD(1, "学生pad"),
    TEA_PAD(2, "老师pad"),
    STU_WEB(3, "学生空间"),
    TEA_WEB(4, "教师空间")
    private int code

    private String desc

    Identity(int code, String desc) {
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
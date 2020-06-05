package com.okay.family.common.enums

enum Environment {

    DEV(1, "dev测试环境"),
    HOTFIX(2, "hotfix测试环境"),
    STRESS(3, "stress测试环境"),
    ONLINE(4, "线上环境")

    private int code

    private String desc

    Environment(int code, String desc) {
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
package com.okay.family.common.enums

enum Environment {

    DEV(1, "dev测试环境"),
    HOTFIX(2, "hotfix测试环境"),
    STRESS(3, "stress测试环境"),
    ONLINE(4, "线上环境"),
    RD(5, "开发环境")

    int code

    String desc

    Environment(int code, String desc) {
        this.code = code
        this.desc = desc
    }


}
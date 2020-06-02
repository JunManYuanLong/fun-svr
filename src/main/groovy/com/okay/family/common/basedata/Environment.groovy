package com.okay.family.common.basedata

import com.okay.family.fun.base.interfaces.ErrorCode

enum Environment implements ErrorCode {

    DEV(1, "dev测试环境"),
    HOTFIX(2, "hotfix测试环境"),
    STRESS(3, "stress测试环境")


    @Override
    int getcode() {
        return code
    }

    @Override
    String getDesc() {
        return desc
    }
}
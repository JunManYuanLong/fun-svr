package com.okay.family.common.enums

import com.alibaba.fastjson.JSONArray
import com.okay.family.common.bean.common.SimpleBean

enum CaseAvailableStatus {

    OK(1, "可用"),
    NO(2, "不可用"),

    int code

    String desc

    CaseAvailableStatus(int code, String desc) {
        this.code = code
        this.desc = desc
    }

    static JSONArray getAll() {
        JSONArray result = new JSONArray()
        CaseAvailableStatus.values().each {x ->
            result << SimpleBean.getBean(x.getCode(), x.getDesc())
        }
        result
    }

}

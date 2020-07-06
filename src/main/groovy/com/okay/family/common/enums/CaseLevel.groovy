package com.okay.family.common.enums

import com.alibaba.fastjson.JSONArray
import com.okay.family.common.bean.common.SimpleBean

enum CaseLevel {


    DEAFULT(1, "默认等级"),
    HIGH(2, "高运行等级"),
    NORMAL(3, "普通运行等级"),
    LOW(4, "低运行等级")


    int code

    String desc

    CaseLevel(int code, String desc) {
        this.code = code
        this.desc = desc
    }

    static JSONArray getAll() {
        JSONArray result = new JSONArray()
        CaseLevel.values().each {x ->
            result << SimpleBean.getBean(x.getCode(), x.getDesc())
        }
        result
    }

}
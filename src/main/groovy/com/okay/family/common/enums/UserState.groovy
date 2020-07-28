package com.okay.family.common.enums

import com.alibaba.fastjson.JSONArray
import com.okay.family.common.bean.common.SimpleBean

enum UserState {

//    DEFAULT(1, "默认"),//暂时不用
    OK(2, "可用"),
    NO(3, "不可用"),
    CANNOT(4, "无法验证")

    int code

    String desc

    UserState(int code, String desc) {
        this.code = code
        this.desc = desc
    }

    static JSONArray getAll() {
        JSONArray result = new JSONArray()
        UserState.values().each {x ->
            result << SimpleBean.getBean(x.getCode(), x.getDesc())
        }
        result
    }

}
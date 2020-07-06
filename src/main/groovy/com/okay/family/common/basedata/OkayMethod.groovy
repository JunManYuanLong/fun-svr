package com.okay.family.common.basedata

import com.alibaba.fastjson.JSONObject
import com.okay.family.fun.base.bean.AbstractBean
import com.okay.family.fun.frame.SourceCode

class OkayMethod extends SourceCode {

    public static JSONObject getIdResult(def id) {
        return getSimpleJson(OkayConstant.SIMPLER_RESULT_KEY_ID, id)
    }

    public static JSONObject getNameResult(def name) {
        return getSimpleJson(OkayConstant.SIMPLER_RESULT_KEY_ID, name)
    }

    public static JSONObject getSimpleResult(AbstractBean bean) {
        try {
            getIdResult bean.getProperty(OkayConstant.SIMPLER_RESULT_KEY)
        } catch (MissingPropertyException e) {
            getIdResult(-2)
        }
    }

}

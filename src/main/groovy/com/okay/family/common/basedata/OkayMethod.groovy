package com.okay.family.common.basedata

import com.alibaba.fastjson.JSONObject
import com.okay.family.fun.base.bean.AbstractBean
import com.okay.family.fun.frame.SourceCode

class OkayMethod extends SourceCode {

    public static JSONObject getSimplerResult(def id) {
        return getSimplerJson(OkayConstant.SIMPLER_RESULT_KEY, id)
    }

    public static JSONObject getSimplerResult(AbstractBean bean) {
        try {
            getSimplerResult bean.getProperty(OkayConstant.SIMPLER_RESULT_KEY)
        } catch (MissingPropertyException e) {
            getSimplerResult(-2)
        }
    }

}

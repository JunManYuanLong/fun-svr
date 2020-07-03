package com.okay.family.common.basedata

import com.alibaba.fastjson.JSONObject
import com.okay.family.fun.base.bean.AbstractBean
import com.okay.family.fun.frame.SourceCode

class OkayMethod extends SourceCode {

    public static JSONObject getSimpleResult(def id) {
        return getSimpleJson(OkayConstant.SIMPLER_RESULT_KEY, id)
    }

    public static JSONObject getSimpleResult(AbstractBean bean) {
        try {
            getSimpleResult bean.getProperty(OkayConstant.SIMPLER_RESULT_KEY)
        } catch (MissingPropertyException e) {
            getSimpleResult(-2)
        }
    }

}

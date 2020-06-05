package com.okay.family.utils

import com.alibaba.fastjson.JSONObject
import com.okay.family.common.bean.testcase.CaseVerifyBean
import com.okay.family.fun.frame.SourceCode

class VerifyResponseUtil extends SourceCode {

/**
 * 校验响应结果
 * @param response
 * @param beans
 * @return
 */
    static boolean verify(JSONObject response, List<CaseVerifyBean> beans) {
        String content = response.getIntValue("code") == TEST_ERROR_CODE ? response.getString("content") : response.toString()
        beans.each {

            def key = it.getKey()
            if (key.contains("code")) {
                String str = "\"" + key + "\":" + it.getValue()
                it.setResult(it.getDescription() + "校验结果:" + content.contains(str))
            } else if (key.contains("regex")) {
                it.setResult(it.getDescription() + "校验结果:" + content ==~ it.getValue())
            }

        }

        beans.every {
            it.getResult().endsWith("true")
        }


    }

/**
 * 获取code通用方法
 * @param response
 * @return
 */
    static int getCode(JSONObject response) {
        def str = response.toString()
        def matcher = str =~ /code":\d+/
        matcher.find() ? changeStringToInt(matcher[0] - "code\":") : TEST_ERROR_CODE
    }

}

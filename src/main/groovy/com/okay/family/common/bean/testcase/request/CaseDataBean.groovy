package com.okay.family.common.bean.testcase.request

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.okay.family.common.bean.testcase.CaseVerifyBean
import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Length

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

class CaseDataBean extends AbstractBean {

    private static final long serialVersionUID = -629048686822729332L;

    int id

    @Min(value = 1L)
    int uid

    @Min(value = 1L)
    int apiId

    @NotNull
    JSONArray header

    @NotNull
//    @Pattern(regexp = "")
    String httptype

    @NotNull
    @Length(min = 1, max = 1, message = "读写方式传参格式错误,应该一个字符长度")
    String readType

    @Min(value = 1L)
    int serviceId

    @NotNull
    List<CaseVerifyBean> testWish

    @NotNull
    JSONArray upData

    /**
     * 接口地址
     */
    @Pattern(regexp = "/.+", message = "接口名称应该以/开头!")
    String url


    int available

    JSONObject headers

    JSONObject params


    void init() {
        initHeader()
        initParams()
        available = testWish.size() > 0
    }

    private def initHeader() {
        JSONObject total = new JSONObject()
        if (header.size() > 0) {
            header.each {x ->
                JSONObject json = JSON.parseObject(JSONObject.toJSONString(x))
                def key = json.getString("text")
                def value = json.getString("value")
                total.put(key, value)
            }
        }
        headers = total
    }

    private def initParams() {
        JSONObject total = new JSONObject()
        if (upData.size() > 0) {
            upData.each {x ->
                JSONObject json = JSON.parseObject(JSON.toJSONString(x))

                def key = json.getString("text")
                def value = json.getString("value")
                def type = json.getString("dataType")
                if (type.equalsIgnoreCase("")) {

                } else if (type.equalsIgnoreCase("")) {
                    //todo:处理不同类型的嵌套数据
                    total.put(key, value)
                }
            }
        }
        params = total
    }

}

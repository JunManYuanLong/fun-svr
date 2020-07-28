package com.okay.family.common.bean.testcase.request

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.okay.family.common.basedata.OkayConstant
import com.okay.family.common.bean.testcase.CaseVerifyBean
import com.okay.family.common.enums.CaseAvailableStatus
import com.okay.family.common.exception.CommonException
import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Range
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

/**
 * 测试用例数据
 */
class CaseDataBean extends AbstractBean {

    private static final Logger logger = LoggerFactory.getLogger(CaseDataBean.class)

    private static final long serialVersionUID = -629048686822729332L;

    Integer id

    @NotNull(message = "uid不能为空")
    @Min(value = 1L)
    Integer uid

    @NotNull(message = "envId不能为空")
    @Range(min = 1L, max = OkayConstant.ENV, message = "环境ID参数错误")
    Integer envId

    @NotNull(message = "apiId不能为空")
    @Min(value = 1L)
    Integer apiId

    @NotNull(message = "header_paras不能为空")
    JSONArray header_paras

    @NotBlank(message = "httpType不能为空")
    @Pattern(regexp = "GET|POST-FORM|POST-JSON", message = "请求方式传参错误")
    String httpType

    @NotNull(message = "serviceId不能为空")
    @Min(value = 1L)
    Integer serviceId

    @NotBlank(message = "readType不能为空")
    @Pattern(regexp = "R|W", message = "readtype应该为R|W")
    String readType

    @NotNull(message = "testWish不能为空")
    List<CaseVerifyBean> testWish

    @NotNull(message = "request_paras不能为空")
    JSONArray request_paras

    /**
     * 接口地址
     */
    @NotBlank(message = "url不能为空")
    @Pattern(regexp = "/.+", message = "接口名称应该以/开头!")
    String url


    /**
     * 后赋值
     */
    Integer available

    /**
     * 后赋值
     */
    JSONObject headers

    /**
     * 后赋值
     */
    JSONObject params


    def init() {
        if (id == null) CommonException.fail("请求参数错误,id为空")
        initHeader()
        initParams()
        available = testWish.size() > 0 ? CaseAvailableStatus.OK.getCode() : CaseAvailableStatus.NO.getCode()
    }

    private def initHeader() {
        headers = new JSONObject()
        if (header_paras.size() > 0) {
            header_paras.each {x ->
                JSONObject json = JSON.parseObject(JSONObject.toJSONString(x))
                def key = json.getString(OkayConstant.MOCO_KEY)
                def value = json.getString(OkayConstant.MOCO_VALUE)
                headers.put(key, value)
            }
        }
        logger.info("header参数初始化:{}", headers.toString())
    }

    private def initParams() {
        params = new JSONObject()
        if (request_paras.size() > 0) {
            request_paras.each {x ->
                JSONObject json = JSON.parseObject(JSON.toJSONString(x))
                def type = json.getString("dataType")
                if (type ==~ /number|string|integer/) {
                    def key = json.getString(OkayConstant.MOCO_KEY)
                    def value = json.getString(OkayConstant.MOCO_VALUE)
                    params.put(key, value)
                } else if (type == "object") {
                    def key = json.getString(OkayConstant.MOCO_KEY)
                    JSONArray array = json.getJSONArray(OkayConstant.MOCO_CHILDREN)
                    JSONObject param = new JSONObject()
                    if (array.size() == 0) {
                        def value = json.getString(OkayConstant.MOCO_VALUE)
                        params.put(key, value)
                    } else {
                        array.each {y ->
                            JSONObject json2 = JSON.parseObject(JSON.toJSONString(y))
                            def key2 = json2.getString(OkayConstant.MOCO_KEY)
                            def value2 = json2.getString(OkayConstant.MOCO_VALUE)
                            param.put(key2, value2)
                        }
                        params.put(key, param)
                    }
                } else if (type == "array") {
                    /*此处不兼容array<object>*/
                    def key = json.getString(OkayConstant.MOCO_KEY)
                    def value = json.getString(OkayConstant.MOCO_VALUE)
                    if (!value ==~ /[(\d|,)+]/) {
                        value = value.split(",") as List
                    }
                    params.put(key, value)
                }
            }
        }
        logger.info("请求参数初始化:{}", params.toString())
    }

    boolean canRun() {
        this.available < 3
    }


}

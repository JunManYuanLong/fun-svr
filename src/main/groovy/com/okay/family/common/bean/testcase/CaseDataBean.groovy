package com.okay.family.common.bean.testcase

import com.alibaba.fastjson.JSONObject
import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

class CaseDataBean extends AbstractBean{

    private static final long serialVersionUID = -629048686822729332L;

    /**
     * 接口地址
     */
    @Pattern(regexp = "/.+", message = "接口名称应该以/开头!")
    String apipath

    /**
     * 域名
     */
    String host

    /**
     * 用例名
     */
    @NotNull
    String name

    /**
     * 接口类型,1:读;2:写
     */
    @Range(min = 1L, max = 2L)
    int apitype

    /**
     * 最后一次运行结果
     */
    int lastresult

    @Range(min = 1L, max = 3L)
    int method

    JSONObject headers

    JSONObject params

    @Range(min = 0L, max = 4L, message = "用例等级错误")
    int level

    /**
     * 是否可以被当前用户编辑
     */
    boolean editable

    @NotNull
    List<CaseVerifyBean> verify
}

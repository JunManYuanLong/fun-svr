package com.okay.family.common.bean.testcase

import com.alibaba.fastjson.JSONObject
import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

class TestCaseBean extends AbstractBean {

    private static final long serialVersionUID = -159592567071153477L;

    int id
    /**
     * 创建人的id
     */
    @Min(value = 1L, message = "uid错误")
    int uid

    int lasteditor

    @Range(min = 1L, max = 5L, message = "环境设置错误")
    int environment

    /**
     * 关联项目id
     */
    String project
    /**
     * 关联服务id
     */
    int server
    /**
     * 关联接口id
     */
    int apiId
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
     * 接口地址
     */
    @Pattern(regexp = "/.+", message = "接口名称应该以/开头!")
    String apipath
    /**
     * 接口类型,1:读;2:写
     */
    @Range(min = 1L, max = 2L)
    int apiType

    int last

    @Range(min = 1L, max = 3L)
    int method

    @NotNull
    JSONObject headers

    @NotNull
    JSONObject params

    @NotNull
    List<CaseVerifyBean> verifyBeans

}

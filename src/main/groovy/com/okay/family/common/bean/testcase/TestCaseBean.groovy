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
    int owner

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

    String servername

    /**
     * 关联服务模块id
     */
    int module

    String modulename

    /**
     * 关联接口id
     */
    int apiid

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

    int last

    @Range(min = 1L, max = 3L)
    int method

    JSONObject headers

    JSONObject params

    @Range(min = 0L, max = 4L, message = "用例等级错误")
    int level

    /**
     * 是否可以被当前用户运行
     */
    boolean runstatus

    @NotNull
    List<CaseVerifyBean> verify

}

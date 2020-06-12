package com.okay.family.common.bean.testcase

import com.alibaba.fastjson.JSONObject
import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

class TestCaseBean extends AbstractBean {

    private static final long serialVersionUID = -1595563071153477L;

    int id
    /**
     * 创建人的id
     */
    @Min(value = 1L, message = "uid错误")
    int owner

    @Range(min = 1L, max = 5L, message = "环境设置错误")
    int environment

    /**
     * 关联项目id
     */
    //TODO:看数据结构做判断
    @Pattern(regexp = "(\\d*,)*\\d*", message = "关联项目ID格式错误")
    String project

    /**
     * 关联服务id
     */
    int serverid

    /**
     * 关联服务模块id
     */
    @Min(value = 1L)
    int moduleid


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

    /**
     * 最后一次运行结果
     */
    int lastresult

    @Range(min = 1L, max = 3L)
    int method

    JSONObject headers

    JSONObject paramsjson

    JSONObject params

    @Range(min = 0L, max = 4L, message = "用例等级错误")
    int level

    /**
     * 是否可以被当前用户编辑
     */
    boolean editable

    /**
     * 是否完成
     */
    boolean done

    @NotNull
    List<CaseVerifyBean> verify


}

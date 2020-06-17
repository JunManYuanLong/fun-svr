package com.okay.family.common.bean.testcase.request

import com.okay.family.fun.base.bean.AbstractBean

import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

class CaseAttributeBean extends AbstractBean {

    private static final long serialVersionUID = -629232822729332L;

    int id

    int uid

    int envId

    /**
     * 关联服务id
     */
    int serviceId

    /**
     * 关联服务模块id
     */
    int moduleId

    @NotNull
    String name

    List<Integer> projectList

    int apiId

    @NotNull
    @Pattern(regexp = "add|update|copy",message = "类型错误,应该传:add|update|copy")
    String type

}

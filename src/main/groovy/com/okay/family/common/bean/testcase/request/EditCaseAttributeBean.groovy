package com.okay.family.common.bean.testcase.request

import com.okay.family.fun.base.bean.AbstractBean

import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

/**
 * 用例属性编辑
 */
class EditCaseAttributeBean extends AbstractBean {

    private static final long serialVersionUID = -629232822729332L;

    Integer id

    Integer uid

    Integer envId

    /**
     * 关联服务id
     */
    Integer serviceId

    /**
     * 关联服务模块id
     */
    Integer moduleId

    @NotNull
    String name

    List<Integer> projectList

    Integer apiId

    @NotNull
    @Pattern(regexp = "add|update|copy",message = "类型错误,应该传:add|update|copy")
    String type

}

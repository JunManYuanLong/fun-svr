package com.okay.family.common.bean.testcase.request

import com.okay.family.common.basedata.OkayConstant
import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

/**
 * 用例属性编辑
 */
class EditCaseAttributeBean extends AbstractBean {

    private static final long serialVersionUID = -629232822729332L;

    @Min(value = 1L)
    Integer id

    @NotNull(message = "uid不能为空")
    @Min(value = 1L)
    Integer uid

    @NotNull(message = "envId不能为空")
    @Range(min = 1L, max = OkayConstant.ENV, message = "环境变量参数错误")
    Integer envId

    /**
     * 关联服务id
     */
    @NotNull(message = "serviceId不能为空")
    Integer serviceId

    /**
     * 关联服务模块id
     */
    @NotNull(message = "moduleId不能为空")
    Integer moduleId

    @NotBlank(message = "name不能为空")
    @Length(min = 1, max = 60, message = "名字长度错误1-60")
    String name

    @NotNull(message = "projectList不能为空")
    List<Integer> projectList

    @NotNull(message = "apiId不能为空")
    @Min(value = 1L)
    Integer apiId

    @NotNull(message = "type不能为空")
    @Pattern(regexp = "add|update|copy", message = "类型错误,应该传:add|update|copy")
    String type

}

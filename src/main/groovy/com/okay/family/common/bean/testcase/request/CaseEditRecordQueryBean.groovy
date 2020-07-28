package com.okay.family.common.bean.testcase.request

import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

/**
 * 用例编辑记录
 */
class CaseEditRecordQueryBean extends AbstractBean {

    private static final long serialVersionUID = 3289389274983L;

    @NotNull(message = "id不能为空")
    @Min(value = 1L,message = "id错误!")
    Integer id

    @NotNull(message = "pageNum不能为空")
    @Min(value = 1L)
    Integer pageNum

    @NotNull(message = "pageSize不能为空")
    @Range(min = 5L, max = 20L, message = "每页显示数量设置错误")
    Integer pageSize

}

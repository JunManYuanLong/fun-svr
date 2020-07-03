package com.okay.family.common.bean.pubdata.request

import com.okay.family.common.basedata.OkayConstant
import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.*

/**
 * 增加,删除,更新
 */
class EditPubBean extends AbstractBean {

    private static final long serialVersionUID = 8160564161606L;

    Integer id

    @NotNull
    @Min(value = 1L)
    Integer uid

    @NotNull
    @Range(min = 1L, max = OkayConstant.ENV, message = "环境ID参数错误")
    Integer envId

    @NotBlank
    @Length(min = 1, max = 16, message = "name长度错误1-16")
    String name

    /**
     * 'add, update, delete'
     */
    @NotBlank
    @Pattern(regexp = "add|update|delete", message = "请传正确type:add|update")
    String type

}

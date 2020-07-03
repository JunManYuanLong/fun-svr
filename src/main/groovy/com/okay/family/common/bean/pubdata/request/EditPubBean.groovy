package com.okay.family.common.bean.pubdata.request

import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Length

import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
/**
 * 增加,删除,更新
 */
class EditPubBean extends AbstractBean {

    private static final long serialVersionUID = 8160564161606L;

    Integer id

    @Min(value = 1L)
    Integer uid

    @Max(value = 5L)
    Integer envId

    @NotBlank
    @Length(min = 1, max = 16, message = "name长度错误1-16")
    String name

    /**
     * 'add, update, delete'
     */
    @Pattern(regexp = "add|update|delete", message = "请传正确type:add|update")
    String type

}

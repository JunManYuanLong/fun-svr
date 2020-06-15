package com.okay.family.common.bean.pubdata

import com.okay.family.fun.base.bean.AbstractBean

import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern

/**
 * 增加,删除,更新
 */
class EditPubBean extends AbstractBean {

    private static final long serialVersionUID = 8160564161606L;

    int id

    @Min(value = 1L)
    int uid

    @Max(value = 5L)
    int envId

    String name

    /**
     * 'add, update, delete'
     */
    @Pattern(regexp = "add|update|delete", message = "请传正确type:add|update")
    String type

}

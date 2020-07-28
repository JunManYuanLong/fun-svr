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

    @NotNull(message = "uid不能为空")
    @Min(value = 1L)
    Integer uid

    @NotNull(message = "envId不能为空")
    @Range(min = 1L, max = OkayConstant.ENV, message = "环境ID参数错误")
    Integer envId

    @NotBlank(message = "name不能为空")
    @Length(min = 1, max = 20, message = "公共数据集名称最大长度20")
    String name

    /**
     * 'add, update, delete'
     */
    @NotBlank(message = "type不能为空")
    @Pattern(regexp = "add|update|delete", message = "请传正确type:add|update")
    String type

}

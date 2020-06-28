package com.okay.family.common.bean.casecollect.request

import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.Min

class SearchCollectionBean extends AbstractBean {

    private static final long serialVersionUID = -564894151549848961;

    @Min(value = 1L)
    int uid

    int envId

    String groupQuery

    @Min(value = 1L)
    int pageNum

    @Range(min = 5L, max = 20L, message = "每页显示数量设置错误")
    int pageSize

    int result

    /**
     * 区分用例集名称和ID,1:用例集名称,2:id
     */
    int type

    def init() {
        if (StringUtils.isEmpty(groupQuery)) return
        if (SourceCode.isNumber(groupQuery)) type = 2
        else type = 1
    }

}

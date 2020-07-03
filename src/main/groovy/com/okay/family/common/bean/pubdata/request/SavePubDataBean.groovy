package com.okay.family.common.bean.pubdata.request

import com.okay.family.common.bean.pubdata.PubDataDetailBean
import com.okay.family.fun.base.bean.AbstractBean

import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

class SavePubDataBean extends AbstractBean {

    private static final long serialVersionUID = 6293172188474L;

    @Min(value = 1L)
    Integer id

    @Min(value = 1L)
    Integer uid

    @NotEmpty(message = "公共数据不能为空")
    List<PubDataDetailBean> list

}

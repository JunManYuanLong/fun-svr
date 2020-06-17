package com.okay.family.common.bean.pubdata.request

import com.okay.family.common.bean.pubdata.PubDataDetailBean
import com.okay.family.fun.base.bean.AbstractBean

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

class SavePubDataBean extends AbstractBean {

    private static final long serialVersionUID = 6293172188474L;

    int id

    @Min(value = 1L)
    int uid

    @NotNull
    List<PubDataDetailBean> list

}

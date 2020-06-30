package com.okay.family.common.bean.pubdata.response

import com.okay.family.common.bean.pubdata.PubDataDetailBean
import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Length

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class PubDataBean extends AbstractBean {

    private static final long serialVersionUID = 8754205931721071606L;

    Integer id

    @NotNull(message = "公共数据不能为空")
    @Size(min = 1, message = "数据长度错误")
    List<PubDataDetailBean> children

    @Length(min = 1, max = 16, message = "名字长度错误")
    String name

}

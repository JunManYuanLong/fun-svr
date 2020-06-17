package com.okay.family.common.bean.pubdata.response

import com.okay.family.common.bean.pubdata.PubDataDetailBean
import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class PubDataBean extends AbstractBean {

    private static final long serialVersionUID = 8754205931721071606L;

    int id

    @Range(min = 1L, max = 5L, message = "环境设置错误")
    int envId

    @Min(value = 1L)
    int uid

    @NotNull(message = "公共数据不能为空")
    @Size(min = 1, message = "数据长度错误")
    List<PubDataDetailBean> list

    @Length(min = 1, max = 16, message = "名字长度错误")
    String name

}

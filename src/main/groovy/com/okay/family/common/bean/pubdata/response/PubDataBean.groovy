package com.okay.family.common.bean.pubdata.response

import com.okay.family.common.bean.pubdata.PubDataDetailBean
import com.okay.family.fun.base.bean.AbstractBean

class PubDataBean extends AbstractBean {

    private static final long serialVersionUID = 8754205931721071606L;

    Integer id

    List<PubDataDetailBean> children

    String name

}

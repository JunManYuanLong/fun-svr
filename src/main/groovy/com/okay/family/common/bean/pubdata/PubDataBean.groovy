package com.okay.family.common.bean.pubdata

import com.alibaba.fastjson.JSONObject
import com.okay.family.fun.base.bean.AbstractBean

class PubDataBean extends AbstractBean {

    private static final long serialVersionUID = 8754205931721071606L;

    int id

    int owner

    JSONObject data

    String name

    String mark

}

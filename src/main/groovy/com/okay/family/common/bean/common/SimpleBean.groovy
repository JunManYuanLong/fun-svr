package com.okay.family.common.bean.common

import com.okay.family.fun.base.bean.AbstractBean

/**
 * 通用bean,用户返回名称列表
 */
class SimpleBean extends AbstractBean {

    private static final long serialVersionUID = 168461652315615;

    Integer id

    String name

    SimpleBean(Integer id, String name) {
        this.id = id
        this.name = name
    }

    public static SimpleBean getBean(int id, String name) {
        new SimpleBean(id, name)
    }

}

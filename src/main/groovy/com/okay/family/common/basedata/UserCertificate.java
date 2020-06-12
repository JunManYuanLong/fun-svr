package com.okay.family.common.basedata;

import com.okay.family.common.bean.testuser.TestUserBean;

import java.util.concurrent.ConcurrentHashMap;

public class UserCertificate {

    public static ConcurrentHashMap<Integer, TestUserBean> certificates = new ConcurrentHashMap<>();

}

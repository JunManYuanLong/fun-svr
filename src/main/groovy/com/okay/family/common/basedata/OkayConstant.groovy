package com.okay.family.common.basedata

import com.okay.family.fun.config.Constant
import com.okay.family.fun.frame.SourceCode

import java.util.concurrent.atomic.AtomicInteger

class OkayConstant {

    public static final String REQUEST_ID = "requestid"

    public static final String REQUEST_ID_PREFIX = "family"

    public static final String MARK_HEADER = "mark"

    public static final String USER_CERTIFICATE_KEY = "uid="

    public static final String RANDOM_KEY = "random="

    public static final String RULE = "value填写'uid=123'表示使用用户id为123的测试账号登录状态"

    public static final String MYSQL_SEPARATE = Constant.PERCENT[1]

    public static final ROLE = 4L

    public static final long USER_STATUS = 4L

    public static final long ENV = 4L

    public static final long CERTIFICATE_TIMEOUT = 20 * 60 * 1000L

    public static AtomicInteger RUN_MARK = new AtomicInteger(SourceCode.getMark() % 100000000)

    public static AtomicInteger COLLECTION_MARK = new AtomicInteger(RUN_MARK.get() / 100)


}

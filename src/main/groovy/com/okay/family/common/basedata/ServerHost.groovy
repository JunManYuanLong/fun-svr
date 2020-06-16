package com.okay.family.common.basedata

import com.okay.family.common.exception.CommonException
import com.okay.family.fun.frame.SourceCode
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServerHost extends SourceCode {

    static Logger logger = LoggerFactory.getLogger(ServerHost.class)

    static Map<Integer, String> hostlist

    public static String getHost(int id) {
        if (!hostlist.containsKey(id)) CommonException.fail("获取域名错误,服务ID:${id}")
        hostlist.get(id)
    }

    static void init(Map<Integer, String> map) {
        hostlist = map
    }
}

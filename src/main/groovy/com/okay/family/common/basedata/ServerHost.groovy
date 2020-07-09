package com.okay.family.common.basedata

import com.okay.family.common.exception.CommonException
import com.okay.family.fun.frame.SourceCode
import org.apache.commons.lang3.StringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServerHost extends SourceCode {

    static Logger logger = LoggerFactory.getLogger(ServerHost.class)

    static Map<Integer, String> hostlist


    static void init(Map<Integer, String> map) {
        hostlist = map
    }

    public static String getHost(int id) {
        if (!hostlist.containsKey(id)) CommonException.fail("没有改服务域名,服务ID:${id}")
        String get = hostlist.get(id)
        if (StringUtils.isBlank(get)) CommonException.fail("该服务没有域名,服务ID:${id}")
        get
    }

    static String getHost(int serviceId, int envId) {
        getHost(serviceId * 10 + envId)
    }
}

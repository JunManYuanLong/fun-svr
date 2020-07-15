package com.okay.family.common.basedata

import com.okay.family.fun.frame.SourceCode
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.util.concurrent.ConcurrentHashMap

class ServerHost extends SourceCode {

    private static Logger logger = LoggerFactory.getLogger(ServerHost.class)

    static Map<Integer, String> hosts = new ConcurrentHashMap<>()

    static Map<Integer, Integer> timeout = new ConcurrentHashMap<>()

    public static String getHost(int id) {
        if (timeout.get(id) == null || getMark() - timeout.get(id) > 1000) null
        if (!hosts.containsKey(id)) null
        else hosts.get(id)
    }

    static String getHost(int envId, int serviceId) {
        getHost(serviceId * 10 + envId)
    }

    static void putHost(int envId, int serviceId, String host) {
        int key = serviceId * 10 + envId
        timeout.put(key, getMark())
        hosts.put(key, host)
    }

}

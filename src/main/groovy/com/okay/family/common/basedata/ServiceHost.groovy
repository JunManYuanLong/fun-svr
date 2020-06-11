package com.okay.family.common.basedata


import com.okay.family.fun.frame.SourceCode
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServiceHost extends SourceCode {

    static Logger logger = LoggerFactory.getLogger(ServiceHost.class)

    static Map<Integer, Map<String, String>> hostlist = new HashMap<>()

    static Map<Integer, Map<Integer, String>> hostlist2 = new HashMap<>()

    public static String getHost(int environment, String serverName) {
        try {
            hostlist.get(environment).get(serverName)
        } catch (Exception e) {
//            logger.error("获取域名发生错误!,环境:{},服务名:{}", environment, serverName, e)
            "http://pic.automancloud.com"
        }
    }

    public static String getHost(int environment, int serverid) {
        try {
            hostlist2.get(environment).get(serverid)
        } catch (Exception e) {
//            logger.error("获取域名发生错误!,环境:{},服务id:{}", environment, serverid, e)
            "http://pic.automancloud.com"
        }
    }
}

package com.okay.family.common.basedata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class UserCertificate {

    private static Logger logger = LoggerFactory.getLogger(UserCertificate.class);

    public static ConcurrentHashMap<Integer, Object> certificates = new ConcurrentHashMap<>();

    /**
     * 获取锁对象,用户测试用户锁
     *
     * @param id
     * @return
     */
    public static Object get(int id) {
        certificates.compute(id, (key, value) ->
        {
            if (value == null) {
                value = new Object();
            }
            return value;
        });
//        Object o = certificates.get(id);
//        synchronized (o) {
//            //查询数据库,如果为空,登录用户,获取token,写入数据库
//            SourceCode.sleep(1000);
//            logger.info(Time.getNow());
//        }
        return certificates.get(id);
    }


}

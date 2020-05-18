package com.okay.family.common;

import com.okay.family.fun.config.SqlConstant;
import com.okay.family.fun.frame.SourceCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class RequestSave extends SourceCode {

    public static Logger logger = LoggerFactory.getLogger(RequestSave.class);

    static LinkedBlockingQueue<String> sqls = new LinkedBlockingQueue<>();

    public static boolean addWork(String sql) {
        try {
            sqls.put(sql);
        } catch (InterruptedException e) {
            logger.warn("添加数据库存储任务失败！", e);
            return false;
        }
        return true;
    }

    /**
     * 从任务池里面获取任务
     *
     * @return
     */
    static String getWork() {
        String sql = null;
        try {
            sql = sqls.poll(SqlConstant.MYSQLWORK_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            logger.warn("获取存储任务失败！", e);
        } finally {
            return sql;
        }
    }
}

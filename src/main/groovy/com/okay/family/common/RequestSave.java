package com.okay.family.common;

import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.bean.RequestSaveBean;
import com.okay.family.fun.frame.SourceCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class RequestSave extends SourceCode {

    public static Logger logger = LoggerFactory.getLogger(RequestSave.class);

    static LinkedBlockingQueue<RequestSaveBean> beans = new LinkedBlockingQueue<>();

    public static boolean addWork(RequestSaveBean requestSaveBean) {
        try {
            beans.put(requestSaveBean);
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
    public static RequestSaveBean getWork() {
        RequestSaveBean requestSaveBean = null;
        try {
            requestSaveBean = beans.poll(OkayConstant.MYSQLWORK_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            logger.warn("获取存储任务失败！", e);
        } finally {
            return requestSaveBean;
        }
    }


    public static int getWorkNum() {
        return beans.size();
    }
}

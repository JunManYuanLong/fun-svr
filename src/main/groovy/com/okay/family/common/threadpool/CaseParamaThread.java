package com.okay.family.common.threadpool;

import com.okay.family.common.bean.testcase.request.CaseDataBean;
import com.okay.family.fun.frame.SourceCode;
import com.okay.family.utils.HandleCaseParamsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class CaseParamaThread extends SourceCode implements Runnable {

    public static Logger logger = LoggerFactory.getLogger(CaseParamaThread.class);

    CaseDataBean bean;

    ConcurrentHashMap map;

    CountDownLatch countDownLatch;

    public CaseParamaThread(CaseDataBean bean, ConcurrentHashMap map, CountDownLatch countDownLatch) {
        this.bean = bean;
        this.map = map;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            HandleCaseParamsUtil.handleParams(bean, map);
        } catch (Exception e) {
            logger.error("处理用例参数发生错误,用例信息:{}", bean.toString(), e);
        }finally {
            countDownLatch.countDown();
        }
    }


}

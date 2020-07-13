package com.okay.family.common;

import com.alibaba.fastjson.JSONObject;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.bean.testcase.CaseRunRecord;
import com.okay.family.common.bean.testcase.request.CaseDataBean;
import com.okay.family.common.enums.CaseAvailableStatus;
import com.okay.family.common.enums.RunResult;
import com.okay.family.utils.RunCaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class CaseRunThread implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(CaseRunThread.class);

    int envId;

    CaseDataBean bean;

    CaseRunRecord record;

    CountDownLatch countDownLatch;

    public CaseRunRecord getRecord() {
        return record;
    }

    private CaseRunThread() {

    }

    public CaseRunThread(CaseDataBean bean, CountDownLatch countDownLatch, int runId, int envId) {
        this.bean = bean;
        this.envId = envId;
        this.countDownLatch = countDownLatch;
        this.record = new CaseRunRecord();
        record.setRunId(runId);
        record.setUid(bean.getUid());
        record.setParams(bean.getParams());
        record.setCaseId(bean.getId());
        record.setMark(OkayConstant.RUN_MARK.getAndIncrement());
        bean.getHeaders().put(OkayConstant.MARK_HEADER, record.getMark());
        record.setHeaders(bean.getHeaders());
    }

    @Override
    public void run() {
        try {
            if (bean.getAvailable() == RunResult.USER_ERROR.getCode()) {
                record.setResponseResult(new JSONObject());
                record.setCode(OkayConstant.TEST_ERROR_CODE);
                record.setResult(RunResult.USER_ERROR.getCode());
                record.setCheckResult(bean.getTestWish());
            } else if (bean.getEnvId() != envId || bean.getAvailable() != CaseAvailableStatus.OK.getCode()) {
                record.setResponseResult(new JSONObject());
                record.setCode(OkayConstant.TEST_ERROR_CODE);
                record.setResult(RunResult.UNRUN.getCode());
                record.setCheckResult(bean.getTestWish());
            } else {
                RunCaseUtil.run(bean, record);
            }
        } catch (Exception e) {
            logger.warn("用例无法运行,ID:{}" + bean.getId(), e);
            record.setResult(RunResult.UNRUN.getCode());
        } finally {
            countDownLatch.countDown();
        }
    }


}

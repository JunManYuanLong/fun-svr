package com.okay.family.common;

import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.bean.testcase.CaseRunRecord;
import com.okay.family.common.bean.testcase.request.CaseDataBean;
import com.okay.family.common.enums.RunResult;
import com.okay.family.utils.RunCaseUtil;

import java.util.concurrent.CountDownLatch;

public class CaseRunThread implements Runnable {

    CaseDataBean bean;

    CaseRunRecord record;

    CountDownLatch countDownLatch;

    public CaseRunRecord getRecord() {
        return record;
    }

    private CaseRunThread() {

    }

    public CaseRunThread(CaseDataBean bean, CountDownLatch countDownLatch, int runId) {
        this.bean = bean;
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
            RunCaseUtil.run(bean, record);
        } catch (Exception e) {
            record.setResult(RunResult.UNRUN.getCode());
        } finally {
            countDownLatch.countDown();
        }
    }


}

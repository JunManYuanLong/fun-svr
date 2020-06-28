package com.okay.family.common;

import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.bean.testcase.CaseRunRecord;
import com.okay.family.common.bean.testcase.request.CaseDataBean;
import com.okay.family.common.enums.RunResult;
import com.okay.family.common.exception.CommonException;
import com.okay.family.common.exception.UserStatusException;
import com.okay.family.utils.RunCaseUtil;

import java.util.concurrent.CountDownLatch;

public class CaseRunThread implements Runnable {

    int runId;

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
        this.runId = runId;
        this.record = new CaseRunRecord();
    }

    @Override
    public void run() {
        try {
            record = RunCaseUtil.run(bean);
        } catch (UserStatusException e) {
            record.setResult(RunResult.USER_ERROR.getCode());
        } catch (CommonException e) {
            record.setResult(RunResult.UNRUN.getCode());
        } catch (Exception e) {
            record.setResult(RunResult.UNRUN.getCode());
        } finally {
            countDownLatch.countDown();
        }
    }

    private void init() {
        record.setMark(OkayConstant.RUN_MARK.getAndIncrement());
        record.setRunId(runId);
        record.setCaseId(bean.getId());
        //todo:完成基本功能的初始化
    }


}

package com.okay.family.common;

import com.okay.family.common.bean.testcase.CaseRunRecord;
import com.okay.family.common.bean.testcase.request.CaseDataBean;
import com.okay.family.utils.RunCaseUtil;

public class CaseRunThread implements Runnable {

    CaseDataBean bean;

    CaseRunRecord record;


    public CaseRunThread(CaseDataBean bean) {
        this.bean = bean;
    }

    @Override
    public void run() {
        record = RunCaseUtil.run(bean);
    }


}

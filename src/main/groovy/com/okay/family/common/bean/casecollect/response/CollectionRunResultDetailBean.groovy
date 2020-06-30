package com.okay.family.common.bean.casecollect.response

import com.alibaba.fastjson.JSONArray
import com.okay.family.common.bean.testcase.response.ListCaseRunResultBean
import com.okay.family.fun.base.bean.AbstractBean
/**
 * 返回给前端的测试结果
 */
class CollectionRunResultDetailBean extends AbstractBean {

    private static final long serialVersionUID = 32454657765756;
    /**
     * 用例集id
     */
    Integer id

    String name

    String envName

    Integer runId

    Integer caseNum

    String result

    JSONArray list

    String startTime

    String endTime

    List<ListCaseRunResultBean> caseList

    def copyFrom(CollectionRunResultDetailBean bean) {
        this.id = bean.getId()
        this.name = bean.getName()
        this.envId = bean.getEnv()
        this.runId = bean.getRunId()
        this.caseNum = bean.getCaseNum()
        this.list = bean.getList()
        this.result = bean.getResult()
        this.startTime = bean.getStartTime()
        this.endTime = bean.getEndTime()
    }
}

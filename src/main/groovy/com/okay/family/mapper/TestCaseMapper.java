package com.okay.family.mapper;

import com.okay.family.common.bean.testcase.*;

import java.util.List;

public interface TestCaseMapper {

    /**
     * 保存测试用例
     *
     * @param bean
     * @return
     */
    int addCase(TestCaseBean bean);

    List<TestCaseBean> findCasesByApi(int environment, int apiid);

    /**
     * 查找我的测试用例
     *
     * @param uid    用户ID
     * @param aip_id 接口ID
     * @return
     */
    List<TestCaseBean> findMyCases(int uid);

    /**
     * 查找测试用例
     *
     * @param id 测试用例ID
     * @return
     */
    TestCaseBean getCaseInfo(int id);

    /**
     * 编辑测试用例属性
     *
     * @param bean
     */
    //todo:待完成
    int editAttribute(CaseAttributeBean bean);

    /**
     * 编辑测试用例数据
     *
     * @param bean
     */
    //todo:待完成
    int editData(CaseDataBean bean);

    //todo:待完成
    int saveCaseRunRecord(CaseRunRecord bean);

    //todo:待完成
    void saveEditReord(CaseEditRecord record);

    List<TestCaseBean> search(String str);


}

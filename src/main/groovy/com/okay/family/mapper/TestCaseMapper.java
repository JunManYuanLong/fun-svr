package com.okay.family.mapper;

import com.okay.family.common.bean.testcase.TestCaseBean;

import java.util.List;

public interface TestCaseMapper {

    /**
     * 保存测试用例
     *
     * @param bean
     * @return
     */
    int saveCase(TestCaseBean bean);

    /**
     * 查找我的测试用例
     *
     * @param uid    用户ID
     * @param aip_id 接口ID
     * @return
     */
    List<TestCaseBean> findMy(int uid, int aip_id);

    /**
     * 查找测试用例
     *
     * @param id 测试用例ID
     * @return
     */
    TestCaseBean findCase(int id);


}

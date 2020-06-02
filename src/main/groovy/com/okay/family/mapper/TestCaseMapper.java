package com.okay.family.mapper;

import com.okay.family.constants.bean.TestCaseBean;

import java.util.List;

public interface TestCaseMapper {

    int saveCase(TestCaseBean bean);

    List<TestCaseBean> findMy(int uid, int aip_id);


}

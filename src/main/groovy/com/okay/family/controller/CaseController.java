package com.okay.family.controller;

import com.github.pagehelper.PageInfo;
import com.okay.family.common.basedata.OkayMethod;
import com.okay.family.common.bean.common.DelBean;
import com.okay.family.common.bean.common.SimpleBean;
import com.okay.family.common.bean.testcase.CaseRunRecord;
import com.okay.family.common.bean.testcase.request.*;
import com.okay.family.common.bean.testcase.response.*;
import com.okay.family.common.code.CommonCode;
import com.okay.family.common.code.TestCaseCode;
import com.okay.family.fun.base.bean.Result;
import com.okay.family.fun.utils.RString;
import com.okay.family.service.ITestCaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/case")
public class CaseController {

    private static Logger logger = LoggerFactory.getLogger(CaseController.class);

    ITestCaseService service;

    public CaseController(ITestCaseService service) {
        this.service = service;
    }

    @PostMapping(value = "/edit")
    public Result edit(@RequestBody @Valid EditCaseAttributeBean bean) {
        String type = bean.getType();
        if (type.equalsIgnoreCase("add")) {
            int i = service.addCase(bean);
            return i == 1 ? Result.success(OkayMethod.getSimpleResult(bean)) : Result.fail(TestCaseCode.ADD_CASE_FAIL);
        } else if (type.equalsIgnoreCase("update")) {
            service.updateCase(bean);
            return Result.success(OkayMethod.getSimpleResult(bean));
        } else if (type.equalsIgnoreCase("copy")) {
            if (bean.getName() == null) bean.setName(RString.getString(5));
            int i = service.copyCase(bean);
            return i == 1 ? Result.success(OkayMethod.getSimpleResult(bean)) : Result.fail(TestCaseCode.COPY_CASE_FAIL);
        }
        return Result.fail(CommonCode.PARAMS_ERROR);
    }

    @GetMapping(value = "/search")
    public Result searchNoPage(@Valid CaseSearchNoPageBean bean) {
        List<SimpleBean> simpleBeans = service.searchCaseNoPage(bean);
        return Result.success(simpleBeans);
    }

    @PostMapping(value = "/save")
    public Result updateCaseData(@RequestBody @Valid CaseDataBean bean) {
        bean.init();
        int i = service.updateCaseData(bean);
        return Result.success();
    }

    @PostMapping(value = "/del")
    public Result delCase(@RequestBody @Valid DelBean bean) {
        int i = service.delCase(bean);
        return i == 1 ? Result.success() : Result.fail(TestCaseCode.NO_CHANGE_FAIL);
    }

    @GetMapping(value = "/getcases")
    public Result findCaseByid(@Valid CaseSearchBean bean) {
        bean.init();
        PageInfo<TestCaseListBean> testCaseListBeanPageInfo = service.searchCases(bean);
        return Result.success(testCaseListBeanPageInfo);

    }

    @GetMapping(value = "/editrecords")
    public Result findMyCaseByid(@Valid CaseEditRecordQueryBean bean) {
        PageInfo<CaseEditRetrunRecord> caseEditRecords = service.getCaseEditRecords(bean);
        return Result.success(caseEditRecords);

    }

    @GetMapping(value = "/attribute")
    public Result getAttribute(@Valid DelBean bean) {
        TestCaseAttributeBean attributeById = service.getAttributeById(bean.getId());
        return Result.success(attributeById);
    }

    @GetMapping(value = "/data")
    public Result getData(@Valid DelBean bean) {
        CaseDetailBean caseDetail = service.getCaseDetail(bean.getId());
        return Result.success(caseDetail);

    }

    @PostMapping(value = "/run")
    public Result runCase(@RequestBody @Valid CaseDataBean bean) {
        bean.setId(0);
        bean.init();
        CaseRunRecord caseRunRecord = service.runCaseData(bean);
        return Result.success(caseRunRecord);
    }

    @GetMapping(value = "/record")
    public Result getRecord(@Valid DelBean bean) {
        CaseRunDetailBean caseRunRecord = service.getCaseRunRecord(bean.getId());
        return Result.success(caseRunRecord);
    }
}

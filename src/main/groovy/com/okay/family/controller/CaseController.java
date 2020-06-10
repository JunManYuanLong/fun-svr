package com.okay.family.controller;

import com.okay.family.common.bean.testcase.CaseRunRecord;
import com.okay.family.common.bean.testcase.TestCaseBean;
import com.okay.family.common.code.TestCaseCode;
import com.okay.family.fun.base.bean.Result;
import com.okay.family.fun.frame.SourceCode;
import com.okay.family.service.ITestCaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/case")
public class CaseController {

    private static Logger logger = LoggerFactory.getLogger(CaseController.class);

    ITestCaseService service;

    @Autowired
    public CaseController(ITestCaseService service) {
        this.service = service;
    }

    @PostMapping(value = "/add")
    public Result add(@RequestBody @Valid TestCaseBean bean) {
        int i = service.addCase(bean);
        return i == 1 ? Result.success() : Result.fail(TestCaseCode.ADD_CASE_FAIL);
    }

    @GetMapping(value = "/findcase/{uid}/{environment}")
    public Result findCaseByid(@PathVariable(value = "id", required = true) int id,
                               @PathVariable(value = "environment", required = true) int environment) {
        List<TestCaseBean> my = service.findMy(environment, id);
        return Result.success(my);
    }

    @GetMapping(value = "/findmycase/{uid}/{apiid}")
    public Result findMyCaseByid(@PathVariable(value = "uid", required = true) int uid,
                                 @PathVariable(value = "apiid", required = true) int apiid) {
        List<TestCaseBean> my = service.findMy(uid, apiid);
        return Result.success(my);
    }

    @GetMapping(value = "/runcase/{id}")
    public Result runCase(@PathVariable(value = "id", required = true) int id) {
        CaseRunRecord runCaseHistoryBean = service.runCase(id);

        return Result.success(runCaseHistoryBean);
    }

    @GetMapping(value = "/info/{id}")
    public Result getInfo(@PathVariable(value = "id", required = true) int id) {
        TestCaseBean caseBean = service.getCase(id);
        return Result.success(caseBean);
    }


    @GetMapping(value = "/search/{str}")
    public Result search(@PathVariable(value = "str", required = true) String str) {
        if (SourceCode.isNumber(str)) {
            return Result.success(service.getCase(SourceCode.changeStringToInt(str)));
        }
        List<TestCaseBean> search = service.search(str);
        return Result.success(search);
    }


}

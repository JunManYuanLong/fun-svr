package com.okay.family.controller;

import com.okay.family.common.bean.testcase.RunCaseHistoryBean;
import com.okay.family.common.bean.testcase.TestCaseBean;
import com.okay.family.fun.base.bean.Result;
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

    @PostMapping(value = "/save")
    public Result saveCase(@RequestBody @Valid TestCaseBean bean) {
        int i = service.saveCase(bean);
        return Result.success(i == 1 ? true : false);
    }

    @GetMapping(value = "/findcase/{id}")
    public Result findCaseByid() {
        List<TestCaseBean> my = service.findMy(2, 2);
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
        RunCaseHistoryBean runCaseHistoryBean = service.runCase(id);

        return Result.success(runCaseHistoryBean);
    }

    @GetMapping(value = "/info/{id}")
    public Result getInfo(@PathVariable(value = "id", required = true) int id) {
        TestCaseBean caseBean = service.getCase(id);

        return Result.success(caseBean);
    }


}

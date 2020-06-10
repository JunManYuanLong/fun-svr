package com.okay.family.controller;

import com.okay.family.common.bean.pubdata.DelDataBean;
import com.okay.family.common.bean.pubdata.PubDataBean;
import com.okay.family.common.code.PubDataCode;
import com.okay.family.fun.base.bean.Result;
import com.okay.family.fun.frame.Output;
import com.okay.family.service.IPubDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pubdata")
public class PubDataController {

    private static Logger logger = LoggerFactory.getLogger(PubDataController.class);


    IPubDataService pubDataService;

    @Autowired
    public PubDataController(IPubDataService pubDataService) {
        this.pubDataService = pubDataService;
    }

    @GetMapping(value = "/getdatas/{uid}")
    public Result getDatas(@PathVariable(value = "uid", required = true) int uid) {
        List<PubDataBean> datas = pubDataService.getAllDatas(uid);
        Map<Integer, List<PubDataBean>> collect = datas.stream().collect(Collectors.groupingBy(x -> x.getEnvironment()));

        return Result.success(collect);
    }

    @PostMapping(value = "/add")
    public Result addData(@RequestBody @Valid PubDataBean bean) {
        int i = pubDataService.addData(bean);
        Output.output(i);
        return i == 1 ? Result.success() : Result.fail(PubDataCode.ADD_FAIL);
    }

    @PostMapping(value = "/del")
    public Result delData(@RequestBody @Valid DelDataBean bean) {
        int i = pubDataService.delData(bean);

        return i == 1 ? Result.success() : Result.fail(PubDataCode.NO_MATCH_FAIL);
    }

    @PostMapping(value = "/update")
    public Result updateData(@RequestBody @Valid PubDataBean bean) {
        int i = pubDataService.updateData(bean);
        return i == 1 ? Result.success() : Result.fail(PubDataCode.NO_CHANGE_FAIL);
    }


}

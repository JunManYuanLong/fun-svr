package com.okay.family.controller;

import com.okay.family.common.bean.DelBean;
import com.okay.family.common.bean.pubdata.EditPubBean;
import com.okay.family.common.bean.pubdata.PubDataBean;
import com.okay.family.common.bean.pubdata.SavePubDataBean;
import com.okay.family.common.code.CommonCode;
import com.okay.family.common.code.PubDataCode;
import com.okay.family.fun.base.bean.Result;
import com.okay.family.fun.frame.SourceCode;
import com.okay.family.service.IPubDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pubdata")
public class PubDataController {

    private static Logger logger = LoggerFactory.getLogger(PubDataController.class);


    IPubDataService pubDataService;

    @Autowired
    public PubDataController(IPubDataService pubDataService) {
        this.pubDataService = pubDataService;
    }

    @GetMapping(value = "/get")
    public Result getDatas(@RequestParam(value = "uid", required = true) int uid,
                           @RequestParam(value = "envId", required = true) int envId) {
        List<PubDataBean> datas = pubDataService.getDatasByEnv(uid, envId);
        return Result.build(datas);
    }

    @PostMapping(value = "/edit")
    public Result delData(@RequestBody @Valid EditPubBean bean) {
        if (bean.getType().equalsIgnoreCase("update")) {
            int i = pubDataService.updateDataAttribute(bean);
            return i > 0 ? Result.success() : Result.fail(PubDataCode.NO_CHANGE_FAIL);
        } else if (bean.getType().equalsIgnoreCase("add")) {
            int i = pubDataService.addData(bean);
            return i > 0 ? Result.success(SourceCode.getJson("id=" + i)) : Result.fail(PubDataCode.ADD_FAIL);
        }
        logger.warn("未验证的参数:{}", bean.toString());
        return Result.build(CommonCode.PARAMS_ERROR);
    }

    @PostMapping(value = "/del")
    public Result delData(@RequestBody @Valid DelBean bean) {
        int i = pubDataService.delData(bean);
        return i > 0 ? Result.success() : Result.fail(PubDataCode.NO_MATCH_FAIL);
    }

    @PostMapping(value = "/save")
    public Result saveData(@RequestBody @Valid SavePubDataBean bean) {
        int i = pubDataService.saveData(bean);
        return i > 0 ? Result.success() : Result.fail(PubDataCode.NO_CHANGE_FAIL);

    }


}

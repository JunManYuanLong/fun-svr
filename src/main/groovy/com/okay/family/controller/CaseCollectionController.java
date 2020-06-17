package com.okay.family.controller;

import com.okay.family.common.bean.casecollect.CaseCollectionBean;
import com.okay.family.fun.base.bean.Result;
import com.okay.family.service.ICaseCollectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/collect")
public class CaseCollectionController {

    private static Logger logger = LoggerFactory.getLogger(CaseCollectionController.class);

    ICaseCollectionService service;

    @Autowired
    public CaseCollectionController(ICaseCollectionService service) {
        this.service = service;
    }


    @PostMapping(value = "/add")
    public Result addCaseCollection(@RequestBody @Valid CaseCollectionBean bean) {
        int i = service.addCollection(bean);
        return Result.success(i);
    }

    @PostMapping(value = "/edit")
    public Result editname(@RequestBody @Valid CaseCollectionBean bean) {

        return Result.success();
    }

    @PostMapping(value = "/share")
    public Result updateCaseCollection(@RequestBody @Valid CaseCollectionBean bean) {
        return Result.success();

    }

    @PostMapping(value = "/del")
    public Result delCollection(@RequestBody @Valid CaseCollectionBean bean) {
        return Result.success();

    }

    @PostMapping(value = "/delcase")
    public Result delCase(@RequestBody @Valid CaseCollectionBean bean) {
        return Result.success();

    }

    @GetMapping(value = "/cases")
    public Result getCases(@PathVariable(value = "uid", required = true) int uid) {
        return Result.success();

    }

    @GetMapping(value = "/collections")
    public Result getCollections(@PathVariable(value = "uid", required = true) int uid) {
        return Result.success();

    }

    @GetMapping(value = "/run")
    public Result getCollection(@PathVariable(value = "id", required = true) int id) {
        return Result.success();
    }


}

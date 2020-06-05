package com.okay.family.controller;

import com.okay.family.common.bean.casecollect.CaseCollectionBean;
import com.okay.family.fun.base.bean.Result;
import com.okay.family.service.ICaseCollectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/casecollect")
public class CaseCollectionController {

    private static Logger logger = LoggerFactory.getLogger(CaseCollectionController.class);

    @Autowired
    ICaseCollectionService service;

    @PostMapping(value = "/add")
    public Result addCaseCollection(@RequestBody @Valid CaseCollectionBean bean) {

        service.add(bean);

        return Result.success();


    }

    @PostMapping(value = "/update")
    public Result updateCaseCollection(@RequestBody @Valid CaseCollectionBean bean) {

        service.update(bean);

        return Result.success();


    }


}

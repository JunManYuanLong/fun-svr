package com.okay.family.controller;

import com.okay.family.common.bean.casecollect.CaseCollectionBean;
import com.okay.family.fun.base.bean.Result;
import com.okay.family.service.ICaseCollectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/casecollect")
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

    @PostMapping(value = "/update")
    public Result updateCaseCollection(@RequestBody @Valid CaseCollectionBean bean) {
        service.update(bean);
        return Result.success();
    }


    @GetMapping(value = "/collections/{uid}")
    public Result getCollections(@PathVariable(value = "uid", required = true) int uid) {
        List<CaseCollectionBean> collections = service.getCollections(uid);


        return Result.success(collections);
    }

    @GetMapping(value = "/collection/{id}")
    public Result getCollection(@PathVariable(value = "id",required = true)int id) {
        CaseCollectionBean collectionInfo = service.getCollectionInfo(id);

        return Result.success(collectionInfo);
    }
}

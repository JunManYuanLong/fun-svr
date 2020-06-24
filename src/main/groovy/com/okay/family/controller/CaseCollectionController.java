package com.okay.family.controller;

import com.okay.family.common.bean.casecollect.request.AddCollectionBean;
import com.okay.family.common.bean.casecollect.request.CollectionEditBean;
import com.okay.family.common.bean.casecollect.request.DelCaseCollectionRelationBean;
import com.okay.family.common.bean.casecollect.request.RunCollectionBean;
import com.okay.family.common.bean.casecollect.response.ListCaseBean;
import com.okay.family.common.bean.common.DelBean;
import com.okay.family.common.code.CollectionCode;
import com.okay.family.fun.base.bean.Result;
import com.okay.family.service.ICaseCollectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.okay.family.fun.frame.SourceCode.getJson;

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
    public Result addCaseCollection(@RequestBody @Valid AddCollectionBean bean) {
        service.addCollection(bean);
        int id = bean.getId();
        return id > 0 ? Result.success(getJson("id=" + id)) : Result.build(CollectionCode.ADD_COLLECTION_FAIL);
    }

    @PostMapping(value = "/edit")
    public Result editCollection(@RequestBody @Valid CollectionEditBean bean) {
        int i = service.editCollection(bean);
        return i > 0 ? Result.success() : Result.build(CollectionCode.NO_CHANGE_FAIL);
    }

    @PostMapping(value = "/share")
    public Result shareCollection(@RequestBody @Valid CollectionEditBean bean) {
        int i = service.shareCollection(bean);
        return i > 0 ? Result.success() : Result.build(CollectionCode.NO_CHANGE_FAIL);
    }

    @PostMapping(value = "/del")
    public Result delCollection(@RequestBody @Valid DelBean bean) {
        int i = service.delCollection(bean);
        return i > 0 ? Result.success() : Result.build(CollectionCode.DEL_COLLECTION_FAIL);
    }

    @PostMapping(value = "/delcase")
    public Result delCaseFromCollection(@RequestBody @Valid DelCaseCollectionRelationBean bean) {
        int i = service.delCaseFromCollection(bean);
        return i > 0 ? Result.success() : Result.build(CollectionCode.DEL_CASE_FAIL);
    }

    @GetMapping(value = "/cases")
    public Result getCases(@RequestParam(value = "groupId", required = true) int groupId, @RequestParam(value = "uid", required = true) int uid) {
        List<ListCaseBean> cases = service.getCases(groupId, uid);
        return Result.build(cases);
    }

    @GetMapping(value = "/collections")
    public Result getCollections(@PathVariable(value = "uid", required = true) int uid) {
        return Result.success();

    }

    @PostMapping(value = "/run")
    public Result getCollection(@RequestBody @Valid RunCollectionBean bean) {

        return Result.success();
    }


}

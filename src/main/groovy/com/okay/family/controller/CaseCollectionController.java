package com.okay.family.controller;

import com.github.pagehelper.PageInfo;
import com.okay.family.common.basedata.OkayMethod;
import com.okay.family.common.bean.casecollect.request.*;
import com.okay.family.common.bean.casecollect.response.CollectionRunResultDetailBean;
import com.okay.family.common.bean.casecollect.response.CollectionRunSimpleResutl;
import com.okay.family.common.bean.casecollect.response.ListCaseBean;
import com.okay.family.common.bean.casecollect.response.ListCollectionBean;
import com.okay.family.common.bean.common.DelBean;
import com.okay.family.common.bean.common.SimpleBean;
import com.okay.family.common.code.CollectionCode;
import com.okay.family.fun.base.bean.Result;
import com.okay.family.service.ICaseCollectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        if (bean.getId() == null) {
            service.addCollectionCaseRelation(bean);
            return Result.success();
        }
        int i = service.addCollection(bean);
        return i > 0 ? Result.success(OkayMethod.getSimpleResult(bean)) : Result.fail(CollectionCode.ADD_COLLECTION_FAIL);
    }

    @PostMapping(value = "/edit")
    public Result editCollection(@RequestBody @Valid CollectionEditBean bean) {
        int i = service.editCollection(bean);
        return i > 0 ? Result.success(OkayMethod.getSimpleResult(bean.getGroupId())) : Result.fail(CollectionCode.NO_CHANGE_FAIL);
    }

    @PostMapping(value = "/share")
    public Result shareCollection(@RequestBody @Valid CollectionEditBean bean) {
        int i = service.shareCollection(bean);
        return i > 0 ? Result.success() : Result.fail(CollectionCode.NO_CHANGE_FAIL);
    }

    @PostMapping(value = "/del")
    public Result delCollection(@RequestBody @Valid DelBean bean) {
        int i = service.delCollection(bean);
        return i > 0 ? Result.success() : Result.fail(CollectionCode.DEL_COLLECTION_FAIL);
    }

    @PostMapping(value = "/delcase")
    public Result delCaseFromCollection(@RequestBody @Valid DelCaseCollectionRelationBean bean) {
        int i = service.delCaseFromCollection(bean);
        return i > 0 ? Result.success() : Result.fail(CollectionCode.DEL_CASE_FAIL);
    }

    @GetMapping(value = "/cases")
    public Result getCases(@RequestParam(value = "groupId", required = true) int groupId, @RequestParam(value = "uid", required = true) int uid) {
        List<ListCaseBean> cases = service.getCases(groupId, uid);
        return Result.success(cases);
    }

    @GetMapping(value = "/collections")
    public Result getCollections(@Valid SearchCollectionBean bean) {
        bean.init();
        PageInfo<ListCollectionBean> collecions = service.findCollecions(bean);
        return Result.success(collecions);
    }

    @GetMapping(value = "/search")
    public Result searchNoPage(@Valid SearchCollectionNoPageBean bean) {
        List<SimpleBean> simpleBeans = service.searchCollectionNoPage(bean);
        return Result.success(simpleBeans);
    }

    @PostMapping(value = "/run")
    public Result getCollection(@RequestBody @Valid RunCollectionBean bean) {
        CollectionRunSimpleResutl collectionRunSimpleResutl = service.runCollection(bean);
        return Result.success(collectionRunSimpleResutl);
    }

    @GetMapping(value = "/records")
    public Result getRecords(@Valid DelBean bean) {
        List<SimpleBean> records = service.getRecords(bean);
        return Result.success(records);
    }

    @GetMapping(value = "/record")
    public Result getRecord(@Valid DelBean bean) {
        CollectionRunResultDetailBean collectionRunDetail = service.getCollectionRunDetail(bean.getId());
        return Result.success(collectionRunDetail);

    }


}

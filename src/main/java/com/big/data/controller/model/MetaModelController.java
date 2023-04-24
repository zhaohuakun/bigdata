package com.big.data.controller.model;

import com.big.data.bean.model.MetaModel;
import com.big.data.bean.response.CommonResponse;
import com.big.data.service.model.MetaModelService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MetaModelController {

    @Autowired
    private MetaModelService metaModelService;

    @GetMapping("/model/query/{page}/{size}")
    public PageInfo<MetaModel> queryAll(@PathVariable int page, @PathVariable int size, @RequestParam("searchKey") String searchKey) throws Exception {

        return metaModelService.queryAll(new Page(page, size), searchKey);
    }

    @PostMapping("/model/operation")
    public CommonResponse insert(@RequestBody MetaModel metaModel) {
        metaModelService.insert(metaModel);
        return CommonResponse.builder().code(200).message("ok").build();
    }

    @PatchMapping("/model/operation")
    public CommonResponse update(@RequestBody MetaModel metaModel) {
        metaModelService.update(metaModel);
        return CommonResponse.builder().code(200).message("ok").build();
    }

    @DeleteMapping("/model/operation")
    public void delete(@RequestBody MetaModel metaModel) {
        metaModelService.delete(metaModel);
    }

}

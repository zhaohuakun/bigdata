package com.big.data.controller.model;

import com.big.data.bean.model.MetaModel;
import com.big.data.service.model.MetaModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MetaModelController {

    @Autowired
    private MetaModelService metaModelService;

    @GetMapping("/model/query")
    public List<MetaModel> queryAll() {
        return metaModelService.queryAll();
    }

    @PostMapping("/model/operation")
    public void insert(MetaModel metaModel){
        metaModelService.insert(metaModel);
    }

    @PatchMapping("/model/operation")
    public void update(MetaModel metaModel){
        metaModelService.update(metaModel);
    }

    @DeleteMapping("/model/operation")
    public void delete(MetaModel metaModel){
        metaModelService.delete(metaModel);
    }

}

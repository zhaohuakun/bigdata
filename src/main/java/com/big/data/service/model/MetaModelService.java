package com.big.data.service.model;

import com.big.data.bean.model.MetaModel;
import com.big.data.mapper.model.MetaModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaModelService {

    @Autowired
    private MetaModelMapper metaModelMapper;

    public List<MetaModel> queryAll(){
        return metaModelMapper.query();
    }

    public void insert(MetaModel metaModel){
        metaModelMapper.insert(metaModel);
    }

    public void update(MetaModel metaModel){
        metaModelMapper.update(metaModel);
    }

    public void delete(MetaModel metaModel){
        metaModelMapper.delete(metaModel);
    }

}

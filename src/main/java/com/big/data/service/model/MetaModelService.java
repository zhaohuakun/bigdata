package com.big.data.service.model;

import com.big.data.bean.model.MetaModel;
import com.big.data.mapper.model.MetaModelMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaModelService {

    @Autowired
    private MetaModelMapper metaModelMapper;

    public PageInfo<MetaModel> queryAll(Page page, String searchKey){

        return PageHelper.startPage(page.getPageNum(), page.getPageSize())
                .doSelectPageInfo(() -> metaModelMapper.query(searchKey));
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

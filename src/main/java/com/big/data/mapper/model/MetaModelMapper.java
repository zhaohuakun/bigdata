package com.big.data.mapper.model;

import com.big.data.bean.model.MetaModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MetaModelMapper {

    //查询
    List<MetaModel> query();

    void insert(MetaModel metaModel);

    void update(MetaModel metaModel);

    void delete(MetaModel metaModel);
}

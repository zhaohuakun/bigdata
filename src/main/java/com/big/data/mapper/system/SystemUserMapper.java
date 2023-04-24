package com.big.data.mapper.system;

import com.big.data.bean.system.SystemUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SystemUserMapper {

    //查询
    List<SystemUser> query(SystemUser bean);

}

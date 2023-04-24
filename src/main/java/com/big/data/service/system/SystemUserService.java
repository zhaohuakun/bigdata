package com.big.data.service.system;

import com.big.data.bean.system.SystemUser;
import com.big.data.mapper.system.SystemUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemUserService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    public List<SystemUser> query(SystemUser bean){
        return systemUserMapper.query(bean);
    }

}

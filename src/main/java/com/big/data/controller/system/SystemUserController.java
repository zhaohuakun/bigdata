package com.big.data.controller.system;

import com.big.data.bean.system.SystemUser;
import com.big.data.service.system.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SystemUserController {

    @Autowired
    private SystemUserService systemUserService;

    @PostMapping("/login")
    public String queryAll(@RequestBody SystemUser user) {
        List<SystemUser> list = systemUserService.query(user);
        if(CollectionUtils.isEmpty(list)){
            return "{\"data\":{\"code\":401,\"msg\":\"登录失败\",\"success\":false}}";
        }
        return "{\"data\":{\"code\":200,\"msg\":\"登录成功\",\"success\":true}}";
    }

}

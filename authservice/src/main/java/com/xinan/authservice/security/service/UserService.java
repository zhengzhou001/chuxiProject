package com.xinan.authservice.security.service;

import com.alibaba.fastjson.JSON;
import com.xinan.authservice.security.entity.UserEntity;
import com.xinan.distributeCore.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private IBaseService baseService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = (UserEntity) baseService.selectOne("test.selectUser",s);
        if (userEntity==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        //根据用户的id查询用户的权限
        List<String> permissions = baseService.select("test.selectUserPermission",userEntity.getId());
        //将permissions转成数组
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);

        //将userDto转成json
        String principal = JSON.toJSONString(userEntity);
        UserDetails userDetails = User.withUsername(principal).password(userEntity.getPwd()).authorities(permissionArray).build();
        return userDetails;
    }
}

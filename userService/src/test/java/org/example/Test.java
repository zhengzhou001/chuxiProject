package org.example;

import com.xinan.userService.Start;
import com.xinan.userService.sys.entity.SysUserEntity;
import com.xinan.userService.sys.mapper.SysUserMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Start.class)
@RunWith(SpringRunner.class)

public class Test {
    @Autowired
    private SysUserMapper sysUserMapper;


    @org.junit.Test
    public void test(){
        Integer array[]={13};
        sysUserMapper.multiDeleteSysUser(array);
    }

    @org.junit.Test
    public void test2(){
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setAccount("12234");
        sysUserEntity.setState(1);
        sysUserEntity.setName("test");
        sysUserEntity.setPwd("1232435");

        sysUserMapper.insertSysUser(sysUserEntity);
        System.out.println(sysUserEntity.getId());

    }

    @org.junit.Test
    public void test3(){

    }
}

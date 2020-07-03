package com.xinan;

import com.xinan.distributeCore.dao.IBaseDao;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = Start.class)
@RunWith(SpringRunner.class)

public class Test {
    @Autowired
    private IBaseDao baseDao;


    @org.junit.Test
    public void test(){
        Map map = new HashMap<>();
        map.put("CLOB11","aa");
       List<Map> list =  baseDao.select("base.select1",map);
        System.out.println(list);
    }





}

package com.xinan.controller;

import com.xinan.Entity.ClobEntity;
import com.xinan.distributeCore.result.BaseResult;
import com.xinan.distributeCore.service.IBaseService;
import com.xinan.distributeCore.tools.BaseTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    IBaseService baseService;

    @RequestMapping(value={"/selectByEntity"}, method={RequestMethod.POST})
    public BaseResult selectByEntity(@RequestBody ClobEntity clobEntity){
        BaseResult ret = new BaseResult();


        List<ClobEntity> list =  baseService.select("base.selectByEntity",clobEntity);
        ret.setData(list);
        return ret ;
    }

    @RequestMapping(value={"/selectByMap"}, method={RequestMethod.POST})
    public BaseResult selectByMap(@RequestBody Map map){
        BaseResult ret = new BaseResult();
        List<ClobEntity> list =  baseService.select("base.selectByMap",map);
        ret.setData(list);
        return ret ;
    }


    @RequestMapping(value={"/insertByEntity"}, method={RequestMethod.POST})
    public BaseResult insertByEntity(){
        BaseResult ret = new BaseResult();
        String clob1 = "";
        for (int i=0;i<4500;i++){
            clob1+="a";
        }
        ClobEntity clobEntity = new ClobEntity();
      //  clobEntity.setClob1(clob1);
        clobEntity.setId(Long.valueOf(BaseTools.getNextSeq()));
        clobEntity.setName("张三");
        ret.setData(baseService.insert("base.insert",clobEntity));
        return ret ;
    }

    @RequestMapping(value={"/insertByMap"}, method={RequestMethod.POST})
    public BaseResult insertByMap(){
        BaseResult ret = new BaseResult();
        String clob1 = "";
        for (int i=0;i<4500;i++){
            clob1+="a";
        }
        Map map = new HashMap();
        map.put("id",Long.valueOf(BaseTools.getNextSeq()));
        map.put("name","李四");
        map.put("clob1",clob1);
        ret.setData(baseService.insert("base.insertByMap",map));
        return ret ;
    }


    @RequestMapping(value={"/updateByEntity"}, method={RequestMethod.POST})
    public BaseResult updateByEntity(@RequestBody ClobEntity clobEntity){
        BaseResult ret = new BaseResult();
        String clob1 = "";
        for (int i=0;i<4500;i++){
            clob1+="b";
        }
        clobEntity.setClob1(clob1);
        ret.setData(baseService.update("base.updateByEntity",clobEntity));
        return ret ;
    }

    @RequestMapping(value={"/updateByMap"}, method={RequestMethod.POST})
    public BaseResult updateByMap(@RequestBody Map map){
        BaseResult ret = new BaseResult();
        String clob1 = "";
        for (int i=0;i<4500;i++){
            clob1+="c";
        }
        map.put("clob1",clob1);
        ret.setData(baseService.update("base.updateByMap",map));
        return ret ;
    }
}

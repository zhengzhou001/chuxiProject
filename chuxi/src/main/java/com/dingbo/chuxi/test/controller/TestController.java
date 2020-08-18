package com.dingbo.chuxi.test.controller;

import com.xinan.distributeCore.result.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.Set;

@Controller
@RequestMapping(value = "/test")
@ApiIgnore
public class TestController {
    @Resource
    MyWebsocketServer myWebsocketServer;
    @RequestMapping(value = "/getTestData")
    @ResponseBody
    public BaseResult<String> getTestData(){
        BaseResult<String> baseResult = new BaseResult<String>();
        baseResult.setData("OK");
        return baseResult;
    }


    @RequestMapping(value = "/getUser")
    @ResponseBody
    public BaseResult<Set> getUser(){
        BaseResult<Set> baseResult = new BaseResult<>();
        baseResult.setData(myWebsocketServer.getClients().keySet());
        return baseResult;
    }

    @RequestMapping(value = "/sendOne")
    @ResponseBody
    public BaseResult<String> sendOne(String id,String msg){
        BaseResult<String> baseResult = new BaseResult<String>();
        myWebsocketServer.sendOne(id,msg);
        baseResult.setData("OK");
        return baseResult;
    }
    @RequestMapping(value = "/sendAll")
    @ResponseBody
    public BaseResult<String> sendAll(String msg){
        BaseResult<String> baseResult = new BaseResult<String>();
        myWebsocketServer.sendAll(msg);
        baseResult.setData("OK");
        return baseResult;
    }
}

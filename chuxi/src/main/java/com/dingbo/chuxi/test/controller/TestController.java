package com.dingbo.chuxi.test.controller;

import com.xinan.distributeCore.result.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/getTestData")
    @ResponseBody
    public BaseResult<String> getTestData(){
        BaseResult<String> baseResult = new BaseResult<String>();
        baseResult.setData("OK");
        return baseResult;
    }
}

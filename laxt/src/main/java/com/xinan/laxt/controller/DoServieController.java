package com.xinan.laxt.controller;

import com.xinan.distributeCore.result.BaseResult;
import com.xinan.distributeCore.tools.BaseTools;
import com.xinan.laxt.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/")
@Slf4j
public class DoServieController {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private Config config;

    //调用服务入口
    @RequestMapping(value="/doService")
    @ResponseBody
    public BaseResult doService(@RequestBody Map paramMap) throws Exception{
        //增加请求头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("appid","1");
        headers.add("appname","zzlaxt");
        HttpEntity<Map> httpEntity =  new HttpEntity<>(paramMap,headers);
        //发送Http请求
        ResponseEntity<BaseResult> postEntity =  restTemplate.postForEntity(config.getZuul_address()+MapUtils.getString(paramMap, "realurl",""),
                httpEntity, BaseResult.class);
        BaseResult body = postEntity.getBody();
        return body;

    }

    //登录接口
    @RequestMapping(value="/login")
    @ResponseBody
    public BaseResult login(@RequestBody Map paramMap) throws Exception{
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        paramMap.put("ip", BaseTools.getIPAddress(request));
        //获取IP地址
        //增加请求头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("appid","1");
        headers.add("appname","zzlaxt");
        HttpEntity<Map> httpEntity =  new HttpEntity<>(paramMap,headers);
        //发送Http请求
        ResponseEntity<BaseResult> postEntity =  restTemplate.postForEntity(config.getZuul_address()+"user-service/sys/login",
                httpEntity, BaseResult.class);
        BaseResult body = postEntity.getBody();
        return body;

    }

    //登录接口
    @RequestMapping(value="/getConfig")
    @ResponseBody
    public Map  getConfig(@RequestBody Map paramMap) throws Exception{
        Map  map  = new HashMap();
        map.put("zuul_address",config.getZuul_address());
        map.put("yhzx_address",config.getYhzx_address());
        return map;

    }
}

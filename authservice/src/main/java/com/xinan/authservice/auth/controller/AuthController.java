package com.xinan.authservice.auth.controller;

import com.xinan.authservice.auth.entity.AuthToken;
import com.xinan.authservice.auth.service.AuthService;
import com.xinan.distributeCore.result.BaseResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/oauth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    RestTemplate restTemplate;



    @RequestMapping("/loginPage")
    public String toLogin(){
        return "login";
    }


    @RequestMapping("/loginAction")
    @ResponseBody
    public BaseResult<AuthToken> login(String username, String password, HttpServletResponse response){
        //校验参数
        if (StringUtils.isEmpty(username)){
            throw new RuntimeException("请输入用户名");
        }
        if (StringUtils.isEmpty(password)){
            throw new RuntimeException("请输入密码");
        }
        //申请令牌 authtoken
        AuthToken authToken = authService.login(username, password, "xinan", "xinan");


        //返回结果
        return  BaseResult.getInstance(authToken);
    }

    @RequestMapping("/refreshToken")
    @ResponseBody
    public BaseResult<AuthToken> refreshToken(String refreshToken, HttpServletResponse response){
        //校验参数
        if (StringUtils.isEmpty(refreshToken)){
            throw new RuntimeException("请输入refreshToken");
        }

        //申请令牌 authtoken
        AuthToken authToken = authService.refreshToken(refreshToken, "xinan", "xinan");


        //返回结果
        return  BaseResult.getInstance(authToken);
    }

    @RequestMapping("/getApi")
    @ResponseBody
    public BaseResult getApi(String accressToken, HttpServletResponse response){
        //校验参数
        if (StringUtils.isEmpty(accressToken)){
            throw new RuntimeException("accressToken");
        }

       Map requestMap = new HashMap();

        //增加请求头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","bearer "+accressToken);
        HttpEntity<Map> httpEntity =  new HttpEntity<>(requestMap,headers);
        //发送Http请求
        ResponseEntity<BaseResult> postEntity =  restTemplate.postForEntity("http://zuul/user-service/sys/selectSysMenu",
                httpEntity, BaseResult.class);
        BaseResult body = postEntity.getBody();


        //返回结果
        return  body;
    }


}

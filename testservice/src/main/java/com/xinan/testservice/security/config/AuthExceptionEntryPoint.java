package com.xinan.testservice.security.config;

import com.alibaba.fastjson.JSONObject;
import com.xinan.distributeCore.result.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws ServletException {
        Map<String, Object> map = new HashMap<String, Object>();

        String msg =  authException.getMessage();
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        BaseResult result = null;
        try {
            if (StringUtils.containsIgnoreCase(msg,"Full authentication")){
                //Full authentication is required to access this resource
                result = BaseResult.getInstance(-101,"没有令牌") ;
                response.getWriter().write(JSONObject.toJSONString(result));
                return ;
            }
            //Access token expired
            if (StringUtils.containsIgnoreCase(msg,"Access token expired")){
                //Full authentication is required to access this resource
                result = BaseResult.getInstance(-102,"令牌失效") ;
                response.getWriter().write(JSONObject.toJSONString(result));
                return ;
            }
            //Cannot convert access token to JSON
            if (StringUtils.containsIgnoreCase(msg,"Cannot convert access token to JSON")){
                //Full authentication is required to access this resource
                result = BaseResult.getInstance(-103,"令牌解析json失败") ;
                response.getWriter().write(JSONObject.toJSONString(result));
                return ;
            }

            result = BaseResult.getInstance(-999,msg) ;
            response.getWriter().write(JSONObject.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.xinan.authservice.auth.service.impl;

import com.xinan.authservice.auth.entity.AuthToken;
import com.xinan.authservice.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RestTemplate restTemplate;


    public AuthToken login(String username, String password, String clientId, String clientSecret) {
        //1.申请令牌
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type","password");
        body.add("username",username);
        body.add("password",password);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization",this.getHttpBasic(clientId,clientSecret));
        HttpEntity<MultiValueMap<String,String>> requestEntity = new HttpEntity<>(body,headers);


        ResponseEntity<Map> responseEntity = restTemplate.exchange("http://auth-service/oauth/token", HttpMethod.POST, requestEntity, Map.class);
        Map map = responseEntity.getBody();
        if (map == null || map.get("access_token") == null || map.get("refresh_token") == null || map.get("jti") == null){
            //申请令牌失败
            throw new RuntimeException("申请令牌失败");
        }

        //2.封装结果数据
        AuthToken authToken = new AuthToken();
        authToken.setAccessToken((String) map.get("access_token"));
        authToken.setRefreshToken((String) map.get("refresh_token"));
        authToken.setJti((String)map.get("jti"));

        return authToken;
    }
    //刷新令牌
    public AuthToken refreshToken(String refreshToken, String clientId, String clientSecret){
        //1.刷新令牌
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type","refresh_token");
        body.add("refresh_token",refreshToken);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization",this.getHttpBasic(clientId,clientSecret));
        HttpEntity<MultiValueMap<String,String>> requestEntity = new HttpEntity<>(body,headers);

        ResponseEntity<Map> responseEntity = restTemplate.exchange("http://auth-service/oauth/token", HttpMethod.POST, requestEntity, Map.class);
        Map map = responseEntity.getBody();
        if (map == null || map.get("access_token") == null || map.get("refresh_token") == null || map.get("jti") == null){
            //申请令牌失败
            throw new RuntimeException("申请刷新失败");
        }

        //2.封装结果数据
        AuthToken authToken = new AuthToken();
        authToken.setAccessToken((String) map.get("access_token"));
        authToken.setRefreshToken((String) map.get("refresh_token"));
        authToken.setJti((String)map.get("jti"));

        return authToken;
    }

    private String getHttpBasic(String clientId, String clientSecret) {
        String value = clientId+":"+clientSecret;
        byte[] encode = Base64Utils.encode(value.getBytes());
        return "Basic "+new String(encode);
    }
}

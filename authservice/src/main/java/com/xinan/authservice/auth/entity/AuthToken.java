package com.xinan.authservice.auth.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class AuthToken implements Serializable{

    //令牌信息 jwt
    String accessToken;
    //刷新token(refresh_token)
    String refreshToken;
    //jwt短令牌
    String jti;
}
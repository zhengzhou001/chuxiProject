package com.xinan.authservice.auth.service;


import com.xinan.authservice.auth.entity.AuthToken;

public interface AuthService {

    AuthToken login(String username, String password, String clientId, String clientSecret);

    AuthToken refreshToken(String refreshToken, String clientId, String clientSecret);
}

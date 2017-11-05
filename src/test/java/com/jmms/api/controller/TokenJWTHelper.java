package com.jmms.api.controller;

import com.jmms.api.core.jwt.TokenAuthenticationService;
import com.jmms.api.domain.jwt.LoginInfo;

public class TokenJWTHelper {

    private static TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService();
    private static String token;

    public static String getValidToken() {
        if (token == null) {
            LoginInfo loginInfo = tokenAuthenticationService.generateToken("test", 1);
            token = "Bearer " + loginInfo.getToken();
        }
        return token;
    }
}

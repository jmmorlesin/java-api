package com.jmms.api.core.jwt;

import com.jmms.api.domain.jwt.LoginInfo;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class LoginInfoRequestWrapper extends HttpServletRequestWrapper {

    private LoginInfo loginInfo;

    public LoginInfoRequestWrapper(HttpServletRequest request, LoginInfo loginInfo) {
        super(request);
        this.loginInfo = loginInfo;
    }

    @Override
    public ServletInputStream getInputStream() {
        return new LoginInfoInputStream(loginInfo);
    }

}

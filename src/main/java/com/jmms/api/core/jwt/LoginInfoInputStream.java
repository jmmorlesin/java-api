package com.jmms.api.core.jwt;

import com.jmms.api.domain.jwt.LoginInfo;
import com.jmms.api.service.GsonService;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class LoginInfoInputStream extends ServletInputStream {

    private GsonService gsonService = new GsonService();

    private ByteArrayInputStream bais;

    public LoginInfoInputStream(LoginInfo loginInfo) {
        String json = gsonService.parse(loginInfo);
        bais = new ByteArrayInputStream(json.getBytes());
    }

    @Override
    public boolean isFinished() {
        return this.bais.available() == 0;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener listener) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public int read() throws IOException {
        return this.bais.read();
    }

}

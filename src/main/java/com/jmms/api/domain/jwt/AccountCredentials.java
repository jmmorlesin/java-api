package com.jmms.api.domain.jwt;

import com.jmms.api.utils.Decoder;

public class AccountCredentials {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return Decoder.decode(password);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

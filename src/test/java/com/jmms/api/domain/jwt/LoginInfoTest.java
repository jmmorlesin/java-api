package com.jmms.api.domain.jwt;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginInfoTest {

    private final static String USERNAME = "username";
    private final static String TOKEN = "token";
    private final static Long EXPIRATION = 10L;

    @Test
    public void gettersAndSettersAllWorkCorrectly() {
        LoginInfo loginInfo = new LoginInfo();

        runAllSetters(loginInfo);
        assertAllGetters(loginInfo);
    }

    private void assertAllGetters(LoginInfo loginInfo) {
        assertThat(loginInfo.getUsername()).isEqualTo(USERNAME);
        assertThat(loginInfo.getToken()).isEqualTo(TOKEN);
        assertThat(loginInfo.getExpiration()).isEqualTo(EXPIRATION);
    }

    private void runAllSetters(LoginInfo loginInfo) {
        loginInfo.setUsername(USERNAME);
        loginInfo.setToken(TOKEN);
        loginInfo.setExpiration(EXPIRATION);
    }

}
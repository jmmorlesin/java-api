package com.jmms.api.domain.jwt;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountCredentialsTest {

    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";
    private final static String PASSWORD_ENCODED = "cGFzc3dvcmQ=";

    @Test
    public void gettersAndSettersAllWorkCorrectly() {
        AccountCredentials accountCredentials = new AccountCredentials();

        runAllSetters(accountCredentials);
        assertAllGetters(accountCredentials);
    }

    private void assertAllGetters(AccountCredentials accountCredentials) {
        assertThat(accountCredentials.getUsername()).isEqualTo(USERNAME);
        assertThat(accountCredentials.getPassword()).isEqualTo(PASSWORD);
    }

    private void runAllSetters(AccountCredentials accountCredentials) {
        accountCredentials.setUsername(USERNAME);
        accountCredentials.setPassword(PASSWORD_ENCODED);
    }

}
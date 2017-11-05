package com.jmms.api.controller;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.jmms.api.domain.common.ResponseInformation;
import com.jmms.api.domain.jwt.AccountCredentials;
import com.jmms.api.domain.jwt.LoginInfo;
import com.jmms.api.utils.Constants;
import com.jmms.api.utils.Encoder;
import com.jmms.api.utils.Routes;
import com.jmms.api.utils.StringUtils;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;


public class LoginControllerTest extends GenericControllerTest {

    private final String path = Routes.PATH_LOGIN;

    private final static long HOUR_IN_MILLIS = 1000 * 60 * 60;


    @Override
    protected String getPath() {
        return path;
    }


    @Test
    public void testLogin() {
        String json = gsonService.parse(getAccountCredentials());

        ResponseEntity<String> entity = post("", json, false);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        String jsonResponse = entity.getBody();
        ResponseInformation<LoginInfo> response = gsonService.unparse(jsonResponse, LoginInfo.class);
        assertThat(response).isNotNull();

        LoginInfo loginInfo = response.getResponse();
        assertThat(loginInfo.getUsername()).isEqualTo(config.getSecurityUserName());
        assertThat(loginInfo.getToken()).isNotNull();
        assertThat(loginInfo.getExpiration()).isNotNull();

        validateExpiration(loginInfo.getExpiration(), Constants.JWT_DEFAULT_EXPIRATION_DAYS);
    }

    @Test
    public void testLogin5days() {
        Integer expirationDays = 5;

        LoginInfo loginInfo = getLoginInfoWithExpiration(expirationDays);

        validateExpiration(loginInfo.getExpiration(), expirationDays);
    }

    @Test
    public void testLoginExpirationExceedsFromMax() {
        Integer expirationDays = Constants.JWT_MAX_EXPIRATION_DAYS + 5;

        LoginInfo loginInfo = getLoginInfoWithExpiration(expirationDays);

        validateExpiration(loginInfo.getExpiration(), Constants.JWT_MAX_EXPIRATION_DAYS);
    }

    @Test
    public void testLoginInvalidPassword() {
        String json = gsonService.parse(getAccountInvalidCredentials());

        ResponseEntity<String> entity = post("", json, false);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void testLoginInvalidExtraParam() {
        String json = getJsonCredentialsExtraParam();

        ResponseEntity<String> entity = post("", json, false);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void testLoginWithoutCredentials() {
        String json = "";

        ResponseEntity<String> entity = post("", json, false);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }


    private AccountCredentials getAccountCredentials(){
        AccountCredentials accountCredentials = new AccountCredentials();
        accountCredentials.setUsername(config.getSecurityUserName());
        accountCredentials.setPassword(Encoder.encode(config.getSecurityUserPassword()));
        return accountCredentials;
    }

    private AccountCredentials getAccountInvalidCredentials(){
        AccountCredentials accountCredentials = new AccountCredentials();
        accountCredentials.setUsername(config.getSecurityUserName());
        accountCredentials.setPassword(config.getSecurityUserPassword());
        return accountCredentials;
    }

    private String getJsonCredentialsExtraParam(){
        return StringUtils.join("{",
                "\"username\":\"", config.getSecurityUserName(), "\",",
                "\"password\":\"", Encoder.encode(config.getSecurityUserPassword()), "\",",
                "\"extraParam\":\"lala\",",
                "}");
    }

    private void validateExpiration(Long expiration, Integer expectedDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.DATE, expectedDays);

        long time = calendar.getTimeInMillis();

        assertThat(expiration).isBetween(time - HOUR_IN_MILLIS, time + HOUR_IN_MILLIS);
    }

    private LoginInfo getLoginInfoWithExpiration(Integer expirationDays) {
        String json = gsonService.parse(getAccountCredentials());

        ResponseEntity<String> entity = post("/login?expiration=" + expirationDays, null, json, false);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        String jsonResponse = entity.getBody();
        ResponseInformation<LoginInfo> response = gsonService.unparse(jsonResponse, LoginInfo.class);

        assertThat(response).isNotNull();

        LoginInfo loginInfo = response.getResponse();
        assertThat(loginInfo.getExpiration()).isNotNull();

        return loginInfo;
    }

}

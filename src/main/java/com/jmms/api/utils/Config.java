package com.jmms.api.utils;

import com.jmms.api.domain.common.EnvironmentEnum;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Config {

    //Config file properties
    final static String BUILD_VERSION = "build.version";
    final static String APP_NAME = "app.name";
    private final static String APP_VERSION = "app.version";
    private final static String APP_ENVIRONMENT = "app.environment";

    //Applications file properties
    private final static String JWT_SECRET = "jwt.secret";

    //General config file
    private final static String SECURITY_USER_NAME = "security.user.name";
    private final static String SECURITY_USER_PASSWORD = "security.user.password";

    //Files
    private final static String CONFIG_FILE = "build.properties";
    private final static String GENERAL_CONFIG_FILE = "application.properties";

    private static Config instance = null;

    private final Properties configProp = new Properties();


    private Config() {
        loadProperties(CONFIG_FILE);
        loadProperties(GENERAL_CONFIG_FILE);
    }

    private void loadProperties(String fileName) {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
        try {
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    String getProperty(String key) {
        return configProp.getProperty(key);
    }

    public String getBuildVersion() {
        return getProperty(BUILD_VERSION);
    }

    public String getAppVersion() {
        return getProperty(APP_VERSION);
    }

    public String getAppName() {
        return getProperty(APP_NAME);
    }

    public String getJwtSecret() {
        return getProperty(JWT_SECRET);
    }

    public String getSecurityUserName() {
        return getProperty(SECURITY_USER_NAME);
    }

    public String getSecurityUserPassword() {
        return getProperty(SECURITY_USER_PASSWORD);
    }

    public EnvironmentEnum getAppEnvironment() {
        try {
            return EnvironmentEnum.valueOf(getProperty(APP_ENVIRONMENT).toUpperCase());
        } catch (Exception e) {
            return EnvironmentEnum.DEVELOPMENT;
        }
    }

}

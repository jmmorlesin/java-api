package com.jmms.api.utils;

import org.junit.Before;
import org.junit.Test;


public class ConfigTest {

    private Config config = null;

    @Before
    public void setUp() throws Exception {
        config = Config.getInstance();
    }

    @Test
    public void testGetInstance() throws Exception {
        assert config == Config.getInstance();
    }

    @Test
    public void testGetProperty() throws Exception {
        assert config.getProperty(Config.BUILD_VERSION) != null;
    }

    @Test
    public void testGetInvalidProperty() throws Exception {
        assert config.getProperty("InvalidProperty") == null;
    }

    @Test
    public void testGetBuildVersion() throws Exception {
        assert config.getBuildVersion() != null;
    }

    @Test
    public void testGetAppVersion() throws Exception {
        assert config.getAppVersion() != null;
    }

    @Test
    public void testGetName() throws Exception {
        assert config.getAppName() != null;
    }

    @Test
    public void testSpecificMethodWithGenericMethod() throws Exception {
        assert config.getAppName().equals(config.getProperty(Config.APP_NAME));
    }

}
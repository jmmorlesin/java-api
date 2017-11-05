package com.jmms.api.domain.common;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ApplicationTest {

    @Test
    public void testFields() {
        Application application = new Application();

        String name = "Application name";
        String version = "1.0";
        String buildVersion = "1";
        Long time = 100l;

        application.setName(name);
        application.setVersion(version);
        application.setBuild(buildVersion);
        application.setTime(time);

        assertThat(application.getName()).isEqualTo(name);
        assertThat(application.getVersion()).isEqualTo(version);
        assertThat(application.getBuild()).isEqualTo(buildVersion);
        assertThat(application.getTime()).isEqualTo(time);
    }

}

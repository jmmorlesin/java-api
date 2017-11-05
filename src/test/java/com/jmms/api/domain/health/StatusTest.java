package com.jmms.api.domain.health;

import org.junit.Test;
import com.jmms.api.utils.Config;

import static org.assertj.core.api.Assertions.assertThat;

public class StatusTest {

    private final Config config = Config.getInstance();

    @Test
    public void gettersAndSettersAllWorkCorrectly() {
        Status status = new Status();

        assertAllGetters(status);
    }

    private void assertAllGetters(Status status) {
        assertThat(status.getEnvironment()).isEqualTo(config.getAppEnvironment());
        assertThat(status.getVersion()).isEqualTo(config.getBuildVersion());
        assertThat(status.getStartTime()).isPositive();
        assertThat(status.getUpTime()).isPositive();
    }

}
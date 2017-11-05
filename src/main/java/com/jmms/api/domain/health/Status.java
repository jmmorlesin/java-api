package com.jmms.api.domain.health;

import com.jmms.api.domain.common.EnvironmentEnum;
import com.jmms.api.utils.Config;

import java.lang.management.ManagementFactory;


public class Status {

    private final String version;
    private final EnvironmentEnum environment;
    private final long startTime;
    private final long upTime;

    public Status() {
        Config config = Config.getInstance();
        this.version = config.getBuildVersion();
        this.environment = config.getAppEnvironment();
        this.startTime = ManagementFactory.getRuntimeMXBean().getStartTime();
        this.upTime = ManagementFactory.getRuntimeMXBean().getUptime();
    }

    public String getVersion() {
        return version;
    }

    public EnvironmentEnum getEnvironment() {
        return environment;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getUpTime() {
        return upTime;
    }
}

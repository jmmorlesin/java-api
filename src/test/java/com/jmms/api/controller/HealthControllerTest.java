package com.jmms.api.controller;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.jmms.api.domain.common.ResponseInformation;
import com.jmms.api.domain.health.Status;
import com.jmms.api.utils.Config;
import com.jmms.api.utils.Routes;

import static org.assertj.core.api.Assertions.assertThat;


public class HealthControllerTest extends GenericControllerTest {

    private final String path = Routes.PATH_HEALTH;

    @Override
    protected String getPath() {
        return path;
    }

    @Test
    public void testHealthWithoutAuthentication() {
        ResponseEntity<String> entity = getWithoutAuthentication();
        validStatusValidations(entity);
    }

    @Test
    public void testHealthWithAuthentication() {
        ResponseEntity<String> entity = get("");
        validStatusValidations(entity);
    }

    private void validStatusValidations(ResponseEntity<String> entity) {
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        String json = entity.getBody();
        ResponseInformation<Status> response = gsonService.unparse(json, Status.class);

        assertThat(response).isNotNull();

        Status status = response.getResponse();
        assertThat(status.getVersion()).isEqualTo(Config.getInstance().getBuildVersion());
        assertThat(status.getUpTime()).isGreaterThan(0);
        assertThat(status.getStartTime()).isGreaterThan(0);
    }

}

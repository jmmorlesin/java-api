package com.jmms.api.domain.common;

import org.junit.Test;
import com.jmms.api.domain.health.Status;

import static org.assertj.core.api.Assertions.assertThat;


public class ResponseInformationTest {

    @Test
    public void testFields() throws Exception {
        ResponseInformation<Status> responseInformation = new ResponseInformation<>();


        Application application = new Application();
        Request request = new Request();
        Status status = new Status();

        responseInformation.setApplication(application);
        responseInformation.setRequest(request);
        responseInformation.setResponse(status);

        assertThat(responseInformation.getApplication()).isEqualTo(application);
        assertThat(responseInformation.getRequest()).isEqualTo(request);
        assertThat(responseInformation.getResponse()).isEqualTo(status);
    }

}

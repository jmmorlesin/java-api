package com.jmms.api.domain.common;

import org.junit.Test;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;


public class RequestTest {

    @Test
    public void testFields() throws Exception {
        Request request = new Request();

        URI uri = new URI("http://www.google.com");
        String id = "id";

        request.setRequestedUrl(uri);
        request.setId(id);

        assertThat(request.getRequestedUrl()).isEqualTo(uri);
        assertThat(request.getId()).isEqualTo(id);
    }

}

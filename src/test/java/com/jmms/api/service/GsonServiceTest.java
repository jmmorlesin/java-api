package com.jmms.api.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GsonServiceTest {

    @Autowired
    private GsonService gsonService;

    @Test
    public void testJsonValid() {
        String json = "{\"application\": {\"name\": \"Java-API\",\"version\": \"1.0.0\"}}";

       assertThat(gsonService.isJSONValid(json)).isTrue();
    }

    @Test
    public void testJsonInValid() {
        String json = "{\"application\": {\"name\": \"Java-API\",\"version\": }}";

        assertThat(gsonService.isJSONValid(json)).isFalse();
    }


}
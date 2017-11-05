package com.jmms.api.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.jmms.api.service.GsonService;
import com.jmms.api.utils.Config;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class GenericControllerTest extends GenericTest{

    final Config config = Config.getInstance();

    @Autowired
    protected GsonService gsonService;


    protected abstract String getPath();


    protected ResponseEntity<String> getWithoutAuthentication(){
        return get(getPath(), null, false);
    }

    protected ResponseEntity<String> get(String subUrl){
        return get(subUrl, true);
    }

    protected ResponseEntity<String> get(String subUrl, Boolean authorization){
        return get(getPath(), subUrl, authorization);
    }

    protected ResponseEntity<String> post(String jsonBody){
        return post(getPath(), null, jsonBody, true);
    }

    protected ResponseEntity<String> post(String subUrl, String jsonBody, Boolean authorization){
        return post(getPath(), subUrl, jsonBody, authorization);
    }

}

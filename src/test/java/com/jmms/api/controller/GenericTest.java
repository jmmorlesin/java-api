package com.jmms.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import com.jmms.api.utils.Routes;


public class GenericTest {

    @Autowired
    protected TestRestTemplate restTemplate;


    protected ResponseEntity<String> get(String domainPath, String subUrl, Boolean authorization){
        String url = getUrl(domainPath, subUrl);

        HttpHeaders headers = getHeaders(authorization);
        HttpEntity<String> entity = new HttpEntity<String>("", headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }

    protected ResponseEntity<String> delete(String domainPath, String subUrl, Boolean authorization){
        String url = getUrl(domainPath, subUrl);

        HttpHeaders headers = getHeaders(authorization);
        HttpEntity<Object> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
    }

    protected ResponseEntity<String> put(String domainPath, String subUrl, String jsonBody, Boolean authorization){
        String url = getUrl(domainPath, subUrl);

        HttpHeaders headers = getHeaders(authorization);
        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

        return restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
    }

    protected ResponseEntity<String> post(String domainPath, String subUrl, String jsonBody, Boolean authorization){
        String url = getUrl(domainPath, subUrl);

        HttpHeaders headers = getHeaders(authorization);
        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

        return restTemplate.exchange(url, HttpMethod.POST, request, String.class);
    }

    private String getUrl(String domainPath, String subUrl) {
        StringBuilder url = new StringBuilder(Routes.CONTEXT_PATH);
        if (domainPath != null && !domainPath.trim().isEmpty()) {
            url.append(domainPath);
        }
        if (subUrl != null && !subUrl.trim().isEmpty()) {
            url.append("/");
            url.append(subUrl);
        }
        return url.toString();
    }

    private HttpHeaders getHeaders(Boolean authorization){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if (authorization) {
            headers.set(HttpHeaders.AUTHORIZATION, TokenJWTHelper.getValidToken());
        }
        return headers;
    }

}

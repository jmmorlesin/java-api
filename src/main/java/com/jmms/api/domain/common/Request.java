package com.jmms.api.domain.common;

import java.net.URI;


public class Request {

    private URI requestedUrl;
    private String id;

    public URI getRequestedUrl() {
        return requestedUrl;
    }

    public void setRequestedUrl(URI requestedUrl) {
        this.requestedUrl = requestedUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

package com.jmms.api.domain.common;

import java.lang.annotation.Annotation;


public class ResponseAnnotation implements Annotation {

    private Class genericType;

    @Override
    public Class<? extends Annotation> annotationType() {
        return ResponseAnnotation.class;
    }

    public ResponseAnnotation(Class genericType) {
        this.genericType = genericType;
    }

    public Class getGenericType() {
        return genericType;
    }

    public void setGenericType(Class genericType) {
        this.genericType = genericType;
    }
}

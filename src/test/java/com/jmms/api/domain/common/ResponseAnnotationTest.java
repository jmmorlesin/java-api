package com.jmms.api.domain.common;

import org.junit.Test;


public class ResponseAnnotationTest {

    @Test
    public void testFields() throws Exception {
        ResponseAnnotation responseAnnotation = new ResponseAnnotation(ResponseAnnotationTest.class);
        assert ResponseAnnotationTest.class == responseAnnotation.getGenericType();

        assert responseAnnotation.annotationType() == ResponseAnnotation.class;
    }

    @Test
    public void testReplaceGenericType() throws Exception {
        ResponseAnnotation responseAnnotation = new ResponseAnnotation(ResponseAnnotationTest.class);

        responseAnnotation.setGenericType(ResponseAnnotation.class);
        assert ResponseAnnotation.class == responseAnnotation.getGenericType();
    }

}

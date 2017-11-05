package com.jmms.api.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EncoderTest {

    @Test
    public void testEncode() throws Exception {
        String encodedMessage = Encoder.encode("Java-API");
        assertThat(encodedMessage).isEqualTo("SmF2YS1BUEk=");
    }

}
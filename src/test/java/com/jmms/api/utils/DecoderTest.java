package com.jmms.api.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DecoderTest {

    @Test
    public void testDecode() throws Exception {
        String message = Decoder.decode("SmF2YS1BUEk");
        assertThat(message).isEqualTo("Java-API");
    }

}
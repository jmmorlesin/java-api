package com.jmms.api.utils;

import org.apache.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encoder {

    private final static Logger logger = Logger.getLogger(Encoder.class);

    public static String encode(String message) {
        try {
            byte[] encodedMessage = Base64.getEncoder().encode(message.getBytes());
            return new String(encodedMessage, StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error("Invalid message: " + message, e);
            return "";
        }
    }

}

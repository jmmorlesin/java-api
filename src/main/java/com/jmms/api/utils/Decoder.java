package com.jmms.api.utils;

import org.apache.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Decoder {

    private final static Logger logger = Logger.getLogger(Decoder.class);

    public static String decode(String message) {
        try {
            byte[] decodedMessage = Base64.getDecoder().decode(message.getBytes());
            return new String(decodedMessage, StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error("Invalid message: " + message, e);
            return "";
        }
    }

}

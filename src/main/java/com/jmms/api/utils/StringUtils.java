package com.jmms.api.utils;

import org.apache.log4j.Logger;

import javax.ws.rs.core.MultivaluedMap;
import java.util.UUID;
import java.util.stream.Collectors;


public class StringUtils {

    private final static Logger logger = Logger.getLogger(StringUtils.class);

    public static String join(String... texts) {
        StringBuilder sb = new StringBuilder();
        for (String msg: texts) {
            sb.append(msg);
        }
        return sb.toString();
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getStringNotNull(Object param) {
        if (param == null) {
            return "";
        }
        return param.toString();
    }

    public static String generateStringFromMap(MultivaluedMap<String, String> map) {
        return map.keySet()
                .stream()
                .map(i -> join(i, " : ", map.get(i).toString()))
                .collect(Collectors.joining(" | "));
    }

}

package com.jmms.api.utils;

import org.junit.Test;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class StringUtilsTest {

    @Test
    public void testJoin1Param() throws Exception {
        assert "text1".equals(StringUtils.join("text1"));
    }

    @Test
    public void testJoin2Params() throws Exception {
        assert "text1text2".equals(StringUtils.join("text1", "text2"));
    }

    @Test
    public void testJoin3Params() throws Exception {
        assert "text1text2text3".equals(StringUtils.join("text1", "text2", "text3"));
    }

    @Test
    public void testGetUUID() throws Exception {
        assert !StringUtils.getUUID().equals(StringUtils.getUUID());
    }

    @Test
    public void testGetStringNotNull() {
        assert "Test".equals(StringUtils.getStringNotNull("Test"));
    }

    @Test
    public void testGetStringNotNullWithNullValue() {
        assert "".equals(StringUtils.getStringNotNull(null));
    }

    @Test
    public void testGenerateStringFromMap() {
        MultivaluedMap<String, String> map = new MultivaluedHashMap<>();

        List<String> host = new ArrayList<>();
        host.add("localhost:8080");
        map.put("host", host);

        List<String> accept = new ArrayList<>();
        accept.add("text/html");
        accept.add("application/xhtml+xml");
        map.put("accept", accept);

        assertThat(StringUtils.generateStringFromMap(map))
                .isEqualTo("host : [localhost:8080] | accept : [text/html, application/xhtml+xml]");

    }

}
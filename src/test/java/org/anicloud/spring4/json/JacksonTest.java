package org.anicloud.spring4.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by zhaoyu on 15-4-7.
 */

class MyValue {
    private String name;
    private int age;

    MyValue() {
    }

    MyValue(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class JacksonTest {

    private ObjectMapper objectMapper;

    @Before
    public void before() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testObjectMapper() {

    }

}

package org.anicloud.spring4.slf4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaoyu on 15-4-7.
 */
public class Slf4jLogTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(Slf4jLogTest.class.getName());

    @Test
    public void testSlf4jLog() {
        String name = "zhangzhaoyu";
        int age = 12;
        LOGGER.info("my name is {}, my age is {}", name, age);
    }
}

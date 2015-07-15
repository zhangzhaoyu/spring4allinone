package org.anicloud.spring4.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zhaoyu on 15-7-2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/jmsConsumer.xml"
})
public class TopicConsumerTest {
    @Test
    public void testConsumer() {
        System.out.println("test consumer.");
    }
}

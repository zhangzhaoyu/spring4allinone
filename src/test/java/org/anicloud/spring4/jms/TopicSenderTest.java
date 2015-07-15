package org.anicloud.spring4.jms;

import org.anicloud.spring4.application.dto.UserDto;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.JmsUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-7-2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/jms.xml"
})
public class TopicSenderTest {
    @Resource
    private JmsTemplate jmsTemplate;

    public void testSendMessage(final UserDto user) {
       jmsTemplate.send("anicel.topic.app", new MessageCreator() {
           @Override
           public Message createMessage(Session session) throws JMSException {
               return session.createObjectMessage(user);
           }
       });
    }

    @Test
    public void testWrite() {
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(new UserDto(
                1, "zhangzhaoyu"
        ));
        userDtoList.add(new UserDto(
                2, "huangbin"
        ));

        for (UserDto userDto : userDtoList) {
            testSendMessage(userDto);
        }
        System.out.println("send success.");
    }

    @Test
    public void testReceive() {
        ObjectMessage objectMessage = (ObjectMessage) jmsTemplate.receive();
        System.out.println(objectMessage);
        UserDto userDto = (UserDto) objectMessage;
        System.out.println(userDto);
    }
}

package org.anicloud.spring4.infrastructure.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.anicloud.spring4.application.dto.UserDto;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * Created by zhaoyu on 15-7-2.
 */
@Component(value = "consumerMessageListener")
public class ConsumerMessageListener implements MessageListener {
    public void onMessage(Message message) {
        ObjectMessage  objectMessage = (ObjectMessage) message;
        try {
            UserDto userDto = (UserDto) objectMessage.getObject();
            System.out.println(userDto);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

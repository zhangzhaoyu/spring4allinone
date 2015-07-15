package org.anicloud.spring4.infrastructure.jms;

import org.anicloud.spring4.application.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by zhaoyu on 15-7-12.
 */
@Service
public class AlertServiceImpl implements AlertService {
    @Resource
    private JmsTemplate jmsTemplate;
    @Value("${jms.queue.app}")
    private String destinationName;

    public void sendUserInfoDto(final UserDto user) {
        jmsTemplate.send(destinationName, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(user);
            }
        });
    }
}

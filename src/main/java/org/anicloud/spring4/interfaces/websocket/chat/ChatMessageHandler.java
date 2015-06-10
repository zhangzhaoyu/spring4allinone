package org.anicloud.spring4.interfaces.websocket.chat;

import org.anicloud.spring4.interfaces.websocket.dto.MessageDto;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;

/**
 * Created by zhaoyu on 15-5-21.
 */
public class ChatMessageHandler extends TextWebSocketHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ChatMessageHandler.class);
    private static Map<String, WebSocketSession> sessionMaps = new HashMap<String, WebSocketSession>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        LOG.info("session id : {}, message is : {} ", session.getId(), message.getPayload());
        super.handleTextMessage(session, message);
        String email = (String) session.getAttributes().get("email");
        LOG.info("current user email : {}", email);

        String msg = "["+ email + "] : " + message.getPayload();
        MessageDto messageDto = new MessageDto(null, msg);
        TextMessage textMessage = new TextMessage(messageDto.toJson());
        sendMessageToAllUsers(textMessage);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        String email = (String) session.getAttributes().get("email");
        LOG.info("afterConnectionEstablished" + session.getAttributes().get("email"));

        sessionMaps.put((String) session.getAttributes().get("email"), session);
        String message = "****** Welcome! " + email + " TIME: " + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") + " ******";
        MessageDto messageDto = new MessageDto(sessionMaps.keySet(), message);
        TextMessage textMessage = new TextMessage(messageDto.toJson());
        sendMessageToAllUsers(textMessage);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        String email = (String) session.getAttributes().get("email");
        LOG.info("afterConnectionClosed" + email);
        sessionMaps.remove(email);

        String message = "****** Good Bye! " + email + " TIME: " + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") + " ******";
        MessageDto messageDto = new MessageDto(sessionMaps.keySet(), message);

        TextMessage textMessage = new TextMessage(messageDto.toJson());
        sendMessageToAllUsers(textMessage);
    }

    public void sendMessageToAllUsers(TextMessage message) throws IOException {
        Collection<WebSocketSession> webSocketSessions = sessionMaps.values();
        for (WebSocketSession session : webSocketSessions) {
            if (session.isOpen()) {
                session.sendMessage(message);
            }
        }
    }

    public Set<String> extractOnLineUser(Map<String, WebSocketSession> sessionMaps) {
        Set<String> emailSet = sessionMaps.keySet();
        return emailSet;
    }
}

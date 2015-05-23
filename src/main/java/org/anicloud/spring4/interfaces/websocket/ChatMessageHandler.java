package org.anicloud.spring4.interfaces.websocket;

import org.hamcrest.generator.qdox.JavaDocBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaoyu on 15-5-21.
 */
public class ChatMessageHandler extends TextWebSocketHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ChatMessageHandler.class);

    private static Map<String, WebSocketSession> users = new HashMap<String, WebSocketSession>();

    public ChatMessageHandler() {}

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        users.put(session.getId(), session);
        // handler outline message
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }

    /**
     * send message to the only user
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        WebSocketSession session = users.get(userName);
        if (session.isOpen()) {
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * send messages to all users
     * @param message
     */
    public void sendMessageToAllUsers(TextMessage message) {
        Collection<WebSocketSession> sessions = users.values();
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
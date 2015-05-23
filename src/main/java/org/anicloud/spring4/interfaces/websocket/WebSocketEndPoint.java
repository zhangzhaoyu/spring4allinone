package org.anicloud.spring4.interfaces.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by zhaoyu on 15-5-21.
 */
public class WebSocketEndPoint extends TextWebSocketHandler {

    private String messageContext = "";

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        messageContext += message.getPayload();
        TextMessage returnMessage = new TextMessage(messageContext + " received at server");
        session.sendMessage(returnMessage);
    }

    // handler the outline message
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        session.sendMessage(new TextMessage(messageContext));
    }
}

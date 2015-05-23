package org.anicloud.spring4.interfaces.websocket;

import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

/**
 * Created by zhaoyu on 15-5-22.
 */
public class ChatWebSocketHandlerDecorator extends WebSocketHandlerDecorator {
    public ChatWebSocketHandlerDecorator(WebSocketHandler delegate) {
        super(delegate);
    }
}

package org.anicloud.spring4.interfaces.websocket.chat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

/**
 * Created by zhaoyu on 15-5-21.
 */
@Configuration
@EnableWebSocket
public class ChatWebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        WebSocketHandler webSocketHandler = new WebSocketHandlerDecorator(chatMessageHandler());

        webSocketHandlerRegistry.addHandler(chatMessageHandler(), "/sockjs/chat")
                .withSockJS();
        webSocketHandlerRegistry.addHandler(chatMessageHandler(), "/websocket/chat");
                // passing HTTP session attributes to the WebSocket session attributes
                // session id under the key HTTP_SESSION_ID_ATTR_NAME
                //.addInterceptors(new HttpSessionHandshakeInterceptor());
    }

    @Bean
    public WebSocketHandler chatMessageHandler() {
        return new ChatMessageHandler();
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        return container;
    }
}

package org.anicloud.spring4.interfaces.websocket.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by zhaoyu on 15-5-23.
 */
public class ChatHandShakeInterceptor extends HttpSessionHandshakeInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(ChatHandShakeInterceptor.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        String email = (String) attributes.get("email");
        LOG.info("before hand shake, email is : {}", email);
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
            HttpSession session = serverHttpRequest.getServletRequest().getSession(false);
            if (session != null) {
                String userName = (String) session.getAttribute("email");
                attributes.put("username", userName);
            }
        }
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception ex) {
        LOG.info("after hand shake.");
        super.afterHandshake(request, response, wsHandler, ex);
    }


}

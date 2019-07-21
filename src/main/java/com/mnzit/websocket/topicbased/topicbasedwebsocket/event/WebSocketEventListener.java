package com.mnzit.websocket.topicbased.topicbasedwebsocket.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@Component
public class WebSocketEventListener {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
//        System.out.println("Session connnected");
    }

    @EventListener
    private void handleSessionDisconnected(SessionDisconnectEvent event) {
        System.out.println("DISCONNECED:"+event.getSessionId());
    }

    @EventListener
    private void handleSessionSubscribe(SessionSubscribeEvent event) {
//        System.out.println("Session subscribed");
    }

    @EventListener
    private void handleSessionUnsubscribe(SessionUnsubscribeEvent event) {
//        System.out.println("Session unsubscribed");
    }
}

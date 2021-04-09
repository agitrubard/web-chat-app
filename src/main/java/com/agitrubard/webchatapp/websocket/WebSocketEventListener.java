package com.agitrubard.webchatapp.websocket;

import com.agitrubard.webchatapp.model.enums.UserStatus;
import com.agitrubard.webchatapp.model.response.DisconnectedUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import static com.agitrubard.webchatapp.websocket.config.endpoint.WebSocketConfigEndpoint.WEB_APP;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.debug("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = headerAccessor.getSessionAttributes().get("username").toString();
        if (username != null) {
            log.info(username + " Disconnected to Chat App");

            DisconnectedUserResponse disconnectedUserResponse = DisconnectedUserResponse.builder()
                    .username(username)
                    .userStatus(UserStatus.LEAVE)
                    .build();

            messagingTemplate.convertAndSend(WEB_APP + "/chat", disconnectedUserResponse);
        }
    }
}
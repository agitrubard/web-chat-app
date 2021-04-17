package com.agitrubard.webchatapp.controller;

import com.agitrubard.webchatapp.model.request.LoginRequest;
import com.agitrubard.webchatapp.model.request.MessageRequest;
import com.agitrubard.webchatapp.model.response.LoginResponse;
import com.agitrubard.webchatapp.model.response.MessageResponse;
import com.agitrubard.webchatapp.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import static com.agitrubard.webchatapp.controller.endpoint.ChatControllerEndpoint.*;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @MessageMapping(LOGIN)
    @SendTo(WEB_CHAT_APP)
    public LoginResponse login(@Payload LoginRequest loginRequest, SimpMessageHeaderAccessor headerAccessor) {
        return chatService.login(loginRequest, headerAccessor);
    }

    @MessageMapping(SEND_MESSAGE)
    @SendTo(WEB_CHAT_APP)
    public MessageResponse sendMessage(@Payload MessageRequest messageRequest) {
        return chatService.sendMessage(messageRequest);
    }
}
package com.agitrubard.webchatapp.service.impl;

import com.agitrubard.webchatapp.model.dto.LoginDto;
import com.agitrubard.webchatapp.model.dto.MessageDto;
import com.agitrubard.webchatapp.model.request.LoginRequest;
import com.agitrubard.webchatapp.model.request.MessageRequest;
import com.agitrubard.webchatapp.model.request.converter.LoginRequestConverter;
import com.agitrubard.webchatapp.model.request.converter.MessageRequestConverter;
import com.agitrubard.webchatapp.model.response.LoginResponse;
import com.agitrubard.webchatapp.model.response.MessageResponse;
import com.agitrubard.webchatapp.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChatServiceImpl implements ChatService {

    @Override
    public LoginResponse login(LoginRequest loginRequest, SimpMessageHeaderAccessor headerAccessor) {
        log.info(loginRequest.getUsername() + " Connected to Chat App");

        headerAccessor.getSessionAttributes().put("username", loginRequest.getUsername());

        return getLoginResponse(loginRequest);
    }

    @Override
    public MessageResponse sendMessage(MessageRequest messageRequest) {
        log.info(messageRequest.getSender() + " Sent Message");

        return getMessageResponse(messageRequest);
    }

    private LoginResponse getLoginResponse(LoginRequest loginRequest) {
        LoginDto loginDto = LoginRequestConverter.convert(loginRequest);

        return LoginResponse.builder()
                .username(loginDto.getUsername())
                .userStatus(loginDto.getUserStatus()).build();
    }

    private MessageResponse getMessageResponse(MessageRequest messageRequest) {
        MessageDto messageDto = MessageRequestConverter.convert(messageRequest);

        return MessageResponse.builder()
                .sender(messageDto.getSender())
                .content(messageDto.getContent())
                .userStatus(messageDto.getUserStatus()).build();
    }
}
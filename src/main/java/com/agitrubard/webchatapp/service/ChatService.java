package com.agitrubard.webchatapp.service;

import com.agitrubard.webchatapp.model.request.LoginRequest;
import com.agitrubard.webchatapp.model.request.MessageRequest;
import com.agitrubard.webchatapp.model.response.LoginResponse;
import com.agitrubard.webchatapp.model.response.MessageResponse;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

public interface ChatService {

    LoginResponse login(LoginRequest loginRequest, SimpMessageHeaderAccessor headerAccessor);

    MessageResponse sendMessage(MessageRequest messageRequest);
}
package com.agitrubard.webchatapp.model.request.converter;

import com.agitrubard.webchatapp.model.dto.LoginDto;
import com.agitrubard.webchatapp.model.request.LoginRequest;

public class LoginRequestConverter {

    private LoginRequestConverter() {
    }

    public static LoginDto convert(LoginRequest loginRequest) {
        return LoginDto.builder()
                .username(loginRequest.getUsername())
                .userStatus(loginRequest.getUserStatus())
                .build();
    }
}
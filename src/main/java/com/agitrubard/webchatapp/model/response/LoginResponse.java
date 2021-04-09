package com.agitrubard.webchatapp.model.response;

import com.agitrubard.webchatapp.model.enums.UserStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {

    private String username;
    private UserStatus userStatus;
}
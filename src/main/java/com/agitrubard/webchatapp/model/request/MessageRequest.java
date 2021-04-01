package com.agitrubard.webchatapp.model.request;

import com.agitrubard.webchatapp.model.enums.UserStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MessageRequest {

    private String sender;
    private String content;
    private UserStatus userStatus;
}
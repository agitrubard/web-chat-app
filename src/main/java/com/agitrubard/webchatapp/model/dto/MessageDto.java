package com.agitrubard.webchatapp.model.dto;

import com.agitrubard.webchatapp.model.enums.UserStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MessageDto {

    private String sender;
    private String content;
    private UserStatus userStatus;
}
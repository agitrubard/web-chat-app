package com.agitrubard.webchatapp.model.request.converter;

import com.agitrubard.webchatapp.model.dto.MessageDto;
import com.agitrubard.webchatapp.model.request.MessageRequest;

public class MessageRequestConverter {

    private MessageRequestConverter() {
    }

    public static MessageDto convert(MessageRequest messageRequest) {
        return MessageDto.builder()
                .sender(messageRequest.getSender())
                .content(messageRequest.getContent())
                .userStatus(messageRequest.getUserStatus())
                .build();
    }
}
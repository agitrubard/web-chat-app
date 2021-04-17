package com.agitrubard.webchatapp.controller.endpoint;

import static com.agitrubard.webchatapp.websocket.config.endpoint.WebSocketConfigEndpoint.WEB_APP;

public class ChatControllerEndpoint {

    public static final String WEB_CHAT_APP = WEB_APP + "/chat";
    public static final String LOGIN = "/login";
    public static final String SEND_MESSAGE = "/sendMessage";
}
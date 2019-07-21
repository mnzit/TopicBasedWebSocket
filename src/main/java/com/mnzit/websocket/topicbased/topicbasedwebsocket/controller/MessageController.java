package com.mnzit.websocket.topicbased.topicbasedwebsocket.controller;

import com.mnzit.websocket.topicbased.topicbasedwebsocket.model.UserResponse;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MessageController {

    @MessageMapping("/connect")
    @SendTo("/topic/connect")
    public UserResponse publicConnection(@Payload String username, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", username);
        UserResponse userResponse = new UserResponse(username, "new user joined in public room", new SimpleDateFormat("HH:mm").format(new Date()), UserResponse.MessageType.JOIN, UserResponse.GroupType.PUBLIC);
        return userResponse;
    }

    @MessageMapping("/message")
    @SendTo("/topic/message")
    public UserResponse publicMessage(@Payload UserResponse userResponse) {
        userResponse.setMessageType(UserResponse.MessageType.CHAT);
        userResponse.setGroupType(UserResponse.GroupType.PUBLIC);
        userResponse.setTime(new SimpleDateFormat("HH:mm").format(new Date()));
        return userResponse;
    }

    @MessageMapping("{groupId}/connect")
    @SendTo("/topic/{groupId}/connect")
    public UserResponse privateConnect(@DestinationVariable String groupId, @Payload String username, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", username);
        UserResponse userResponse = new UserResponse(username, "new user joined private room", new SimpleDateFormat("HH:mm").format(new Date()), UserResponse.MessageType.JOIN, UserResponse.GroupType.PRIVATE);
        return userResponse;
    }

    @MessageMapping("{groupId}/message")
    @SendTo("/topic/{groupId}/message")
    public UserResponse privateMessage(@DestinationVariable String groupId, @Payload UserResponse userResponse) {
        userResponse.setMessageType(UserResponse.MessageType.CHAT);
        userResponse.setGroupType(UserResponse.GroupType.PRIVATE);
        userResponse.setTime(new SimpleDateFormat("HH:mm").format(new Date()));
        return userResponse;
    }
}

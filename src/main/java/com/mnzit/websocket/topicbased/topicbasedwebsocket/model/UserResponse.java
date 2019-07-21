package com.mnzit.websocket.topicbased.topicbasedwebsocket.model;

public class UserResponse {

    private String name;
    private String content;
    private String time;
    private MessageType messageType;
    private GroupType groupType;

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

    public enum GroupType {
        PUBLIC, PRIVATE
    }

    public UserResponse(String name, String content, String time, MessageType messageType, GroupType groupType) {
        this.name = name;
        this.content = content;
        this.time = time;
        this.messageType = messageType;
        this.groupType = groupType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }
}

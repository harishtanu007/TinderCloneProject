package com.harish.tinder.model;

public class MessageData {
    String user_id;
    String user_name;
    String type;

    public MessageData(String user_id, String user_name, String type) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.type = type;
    }
}

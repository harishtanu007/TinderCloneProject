package com.harish.tinder.model;

public class FirebaseMessage {
    String to;
    NotifyData notification;
    MessageData data;

    public FirebaseMessage(String to, NotifyData notification, MessageData data) {
        this.to = to;
        this.notification = notification;
        this.data=data;
    }
}

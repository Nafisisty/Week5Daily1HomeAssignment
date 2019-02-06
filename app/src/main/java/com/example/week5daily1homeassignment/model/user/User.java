package com.example.week5daily1homeassignment.model.user;

public class User {

    private String userName;
    private String timeSent;
    private String messegeReceived;

    public User() {
    }

    public User(String userName, String timeSent, String messegeReceived) {
        this.userName = userName;
        this.timeSent = timeSent;
        this.messegeReceived = messegeReceived;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(String timeSent) {
        this.timeSent = timeSent;
    }

    public String getMessegeReceived() {
        return messegeReceived;
    }

    public void setMessegeReceived(String messegeReceived) {
        this.messegeReceived = messegeReceived;
    }
}

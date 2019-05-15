package com.example.iread.model;

public class FriendRequest {
    private String uidSender;
    private String iudResever;

    public FriendRequest() { }

    public String getUidSender() {
        return uidSender;
    }

    public String getIudResever() {
        return iudResever;
    }

    public FriendRequest(String uidSender, String iudResever) {
        this.uidSender = uidSender;
        this.iudResever = iudResever;
    }
}

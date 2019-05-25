package com.example.iread.model;

public class Challenge {
    private String friend;
    private  int Livre;
    private int Time;

    public Challenge(){}
    public Challenge(String friend, int livre, int time) {
        this.friend = friend;
        Livre = livre;
        Time = time;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public int getLivre() {
        return Livre;
    }

    public void setLivre(int livre) {
        Livre = livre;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }
}

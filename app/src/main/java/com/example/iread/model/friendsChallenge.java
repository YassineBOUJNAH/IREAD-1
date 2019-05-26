package com.example.iread.model;

import java.util.Date;

public class friendsChallenge{
    private int Livre2;
    private String uiResever;
    private String uiSender2;
    private Date DateFin;

    public  friendsChallenge(){}
    public friendsChallenge(int livre2, String uiResever, String uiSender2, Date dateFin) {
        Livre2 = livre2;
        this.uiResever = uiResever;
        this.uiSender2 = uiSender2;
        DateFin = dateFin;
    }

    public int getLivre2() {
        return Livre2;
    }

    public void setLivre2(int livre2) {
        Livre2 = livre2;
    }

    public String getUiResever() {
        return uiResever;
    }

    public void setUiResever(String uiResever) {
        this.uiResever = uiResever;
    }

    public String getUiSender2() {
        return uiSender2;
    }

    public void setUiSender2(String uiSender2) {
        this.uiSender2 = uiSender2;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public void setDateFin(Date dateFin) {
        DateFin = dateFin;
    }
}
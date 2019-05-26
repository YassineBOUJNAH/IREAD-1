package com.example.iread.model;

import java.util.Date;

public class friendsChallenge {
    private int Livre;
    private String uiResever;
    private String uiSender;
    private Date DateFin;

    public friendsChallenge(){}
    public friendsChallenge(String uiResever, String uiSender, int livre, Date DateFin) {
        this.uiResever = uiResever;
        this.uiSender = uiSender;
        Livre = livre;
        this.DateFin = DateFin;
    }

    public int getLivre() {
        return Livre;
    }

    public void setLivre(int livre) {
        Livre = livre;
    }


    public String getUiResever() {
        return uiResever;
    }

    public void setUiResever(String uiResever) {
        this.uiResever = uiResever;
    }

    public String getUiSender() {
        return uiSender;
    }

    public void setUiSender(String uiSender) {
        this.uiSender = uiSender;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public void setDateFin(Date DateFin) {
        this.DateFin = DateFin;
    }
}

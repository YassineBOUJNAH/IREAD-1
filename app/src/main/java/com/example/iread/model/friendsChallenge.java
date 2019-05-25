package com.example.iread.model;

public class friendsChallenge {
    private int DateFin;
    private int Livre;
    private String uiResever;
    private String uiSender;

    public friendsChallenge(){}
    public friendsChallenge( String uiResever, String uiSender, int livre, int dateFin) {
        this.uiResever = uiResever;
        this.uiSender = uiSender;
        Livre = livre;
        DateFin = dateFin;
    }

    public int getLivre() {
        return Livre;
    }

    public void setLivre(int livre) {
        Livre = livre;
    }

    public int getDateFin() {
        return DateFin;
    }

    public void setDateFin(int dateFin) {
        DateFin = dateFin;
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
}

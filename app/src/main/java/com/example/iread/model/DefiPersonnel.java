package com.example.iread.model;


public class DefiPersonnel {
    private String titre;
    private String messag;
    private int duree;

    public DefiPersonnel(){ }
    public DefiPersonnel(String titre, String messag, int duree) {
        this.titre = titre;
        this.messag = messag;
        this.duree = duree;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getMessag() {
        return messag;
    }

    public void setMessag(String messag) {
        this.messag = messag;
    }
}

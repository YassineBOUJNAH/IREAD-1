package com.example.iread.model;

public class friendsDefi {
    private String uiResever;
    private int Note;

    public friendsDefi(){}
    public friendsDefi(String uiResever, int note) {
        this.uiResever = uiResever;
        Note = note;
    }
    public String getUiResever() {
        return uiResever;
    }
    public void setUiResever(String uiResever) {
        this.uiResever = uiResever;
    }
    public int getNote() {
        return Note;
    }
    public void setNote(int note) {
        Note = note;
    }
}

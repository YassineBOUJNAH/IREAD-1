package com.example.iread.model;

public class BookFireBase {

    private String authors;
    private String thumbnail;
    private String title;

    public String getAuthors() {
        return authors;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public BookFireBase (){}

    public BookFireBase(String authors, String thumbnail, String title) {
        this.authors = authors;
        this.thumbnail = thumbnail;
        this.title = title;
    }
}

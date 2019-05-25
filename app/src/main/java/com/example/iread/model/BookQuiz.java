package com.example.iread.model;

public class BookQuiz {

    public String auteur;
    public String image;
    public String name;
    public String star;
    //public String classement;

    public BookQuiz(){}
    public BookQuiz(String auteur,String image,String name,String star){
        this.image=image;
        this.name=name;
        this.star=star;
        //this.classement=classement;
    }
    public String getImage(){return image;}
    public String getName(){return name;}
    public String getStar(){return star;}
    public void setImage(String image){this.image =image;}
    public void setName(String name){this.name =name;}
    public void setStar(String star){this.star =star;}
    public String getAuteur(){return auteur;}
    public void setAuteur(String auteur){this.auteur =auteur;}
    //public String getClassement(){return classement;}
    //public void setClassement(String classement){this.classement =classement;}




}

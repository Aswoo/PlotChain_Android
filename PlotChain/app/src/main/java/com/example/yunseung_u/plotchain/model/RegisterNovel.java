package com.example.yunseung_u.plotchain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterNovel {

    @Expose
    @SerializedName("name")
    String name;
    @Expose
    @SerializedName("introduction")
    String introduction;
    @Expose
    @SerializedName("genre")
    int genre;
    @Expose
    @SerializedName("color")
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public RegisterNovel(String name, String introduction, int genre,String color) {
        this.name = name;
        this.introduction = introduction;
        this.genre = genre;
        this.color = color;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}

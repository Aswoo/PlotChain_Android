package com.example.yunseung_u.plotchain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NovelInfo {

    @Expose
    @SerializedName("id")
    private String id;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("author")
    private String author;
    @Expose
    @SerializedName("genre")
    private int genre;
    @Expose
    @SerializedName("state")
    private String state;
    @Expose
    @SerializedName("heart")
    private Double heart;
    @Expose
    @SerializedName("introduction")
    private String introduction;
    @Expose
    @SerializedName("episodeCount")
    private String episodeCount;
    @Expose
    @SerializedName("updatedDate")
    private String updatedDate;
    @Expose
    @SerializedName("createdDate")
    private String createdDate;
    @Expose
    @SerializedName("color")
    private String color;
    @Expose
    @SerializedName("isHeart")
    private boolean isHeart;

    public boolean isHeart() {
        return isHeart;
    }

    public void setHeart(boolean heart) {
        isHeart = heart;
    }

    public String getState() {
        return state;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(String episodeCount) {
        this.episodeCount = episodeCount;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Double getHeart() {
        return heart;
    }

    public void setHeart(Double heart) {
        this.heart = heart;
    }

}

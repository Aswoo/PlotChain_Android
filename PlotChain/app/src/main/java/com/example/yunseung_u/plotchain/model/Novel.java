package com.example.yunseung_u.plotchain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Novel {

    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("genre")
    private String genre;
    @Expose
    @SerializedName("writer")
    private String writer;
    @Expose
    @SerializedName("thumbnail")
    private String thumbnail;
    @Expose
    @SerializedName("shareholding")
    private Double shareholding;
    @Expose
    @SerializedName("status")
    private String status;
    public Novel() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Double getShareholding() {
        return shareholding;
    }

    public void setShareholding(Double shareholding) {
        this.shareholding = shareholding;
    }

    public Novel(String title, String genre, String writer, Double shareholding,String thumbnail,String status) {

        this.title = title;
        this.genre = genre;
        this.writer = writer;
        this.shareholding = shareholding;
        this.thumbnail = thumbnail;
        this.status = status;
    }


}

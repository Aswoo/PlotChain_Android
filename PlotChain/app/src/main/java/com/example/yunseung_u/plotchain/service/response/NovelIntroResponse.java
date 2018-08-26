package com.example.yunseung_u.plotchain.service.response;

import com.example.yunseung_u.plotchain.model.Episode;
import com.example.yunseung_u.plotchain.model.NovelInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.http.Query;

public class NovelIntroResponse {

    @Expose
    @SerializedName("success")
    private String success;
    @Expose
    @SerializedName("errCode")
    private String errCode;
    @Expose
    @SerializedName("name")
    private String title;
    @Expose
    @SerializedName("author")
    private String author;
    @Expose
    @SerializedName("introduction")
    private String introduction;
    @Expose
    @SerializedName("episodeCount")
    private String episodeCount;
    @Expose
    @SerializedName("createDate")
    private String createData;
    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("episode")
    List<Episode> episodes;
    @Expose
    @SerializedName("heart")
    private double heart;
    @Expose
    @SerializedName("genre")
    int genre;
    @Expose
    @SerializedName("color")
    String color;
    @Expose
    @SerializedName("isHeart")
    boolean isHeart;

    public Double getTotalHeart() {
        return totalHeart;
    }

    public void setTotalHeart(Double totalHeart) {
        this.totalHeart = totalHeart;
    }

    @Expose
    @SerializedName("totalHeart")
    Double totalHeart;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isHeart() {
        return isHeart;
    }

    public void setHeart(boolean heart) {
        isHeart = heart;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getCreateData() {
        return createData;
    }

    public void setCreateData(String createData) {
        this.createData = createData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public double getHeart() {
        return heart;
    }
    public void setHeart(double heart) {
        this.heart = heart;
    }
}

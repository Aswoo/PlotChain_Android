package com.example.yunseung_u.plotchain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class History {


    @Expose
    @SerializedName("novel")
    private NovelInfo novelInfo;
    @Expose
    @SerializedName("episode")
    private String episode;
    @Expose
    @SerializedName("readDate")
    private String readDate;

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getReadDate() {
        return readDate;
    }

    public void setReadDate(String readDate) {
        this.readDate = readDate;
    }
    public NovelInfo getNovelInfo() {
        return novelInfo;
    }

    public void setNovelInfo(NovelInfo novelInfo) {
        this.novelInfo = novelInfo;
    }
}

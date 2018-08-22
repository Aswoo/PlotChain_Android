package com.example.yunseung_u.plotchain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GraphHistory {

    @Expose
    @SerializedName("time")
    Long time;
    @Expose
    @SerializedName("heart")
    Long heart;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getHeart() {
        return heart;
    }

    public void setHeart(Long heart) {
        this.heart = heart;
    }
}

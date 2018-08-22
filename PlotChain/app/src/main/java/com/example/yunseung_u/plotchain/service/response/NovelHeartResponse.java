package com.example.yunseung_u.plotchain.service.response;

import com.example.yunseung_u.plotchain.model.GraphHistory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NovelHeartResponse {
    @Expose
    @SerializedName("success")
    String success;
    @Expose
    @SerializedName("errorCode")
    long errCode;
    @Expose
    @SerializedName("history")
    ArrayList<GraphHistory> graphHistories;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public long getErrCode() {
        return errCode;
    }

    public void setErrCode(long errCode) {
        this.errCode = errCode;
    }

    public ArrayList<GraphHistory> getGraphHistories() {
        return graphHistories;
    }

    public void setGraphHistories(ArrayList<GraphHistory> graphHistories) {
        this.graphHistories = graphHistories;
    }

}

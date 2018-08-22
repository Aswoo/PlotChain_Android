package com.example.yunseung_u.plotchain.service.response;

import com.example.yunseung_u.plotchain.model.History;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReadGetResponse {

    @Expose
    @SerializedName("success")
    private String success;
    @Expose
    @SerializedName("errorCode")
    private String errCode;
    @Expose
    @SerializedName("history")
    private ArrayList<History> history;

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

    public ArrayList<History> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<History> history) {
        this.history = history;
    }
}

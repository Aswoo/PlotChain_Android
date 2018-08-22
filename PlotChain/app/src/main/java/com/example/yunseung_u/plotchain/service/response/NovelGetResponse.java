package com.example.yunseung_u.plotchain.service.response;

import com.example.yunseung_u.plotchain.model.Novel;
import com.example.yunseung_u.plotchain.model.NovelInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NovelGetResponse {

    @Expose
    @SerializedName("success")
    private String success;
    @Expose
    @SerializedName("errorCode")
    private String errCode;
    @Expose
    @SerializedName("totalHeart")
    private Double totalHeart;
    @Expose
    @SerializedName("novel")
    private ArrayList<NovelInfo> arrayList;

    public Double getTotalHeart() {
        return totalHeart;
    }

    public void setTotalHeart(Double totalHeart) {
        this.totalHeart = totalHeart;
    }

    public NovelGetResponse(String success, String errCode, ArrayList<NovelInfo> arrayList) {
        this.success = success;
        this.errCode = errCode;
        this.arrayList = arrayList;
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

    public ArrayList<NovelInfo> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<NovelInfo> arrayList) {
        this.arrayList = arrayList;
    }
}

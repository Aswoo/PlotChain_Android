package com.example.yunseung_u.plotchain.service.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EpisodeGetResponse {


    @Expose
    @SerializedName("success")
    private String success;
    @Expose
    @SerializedName("errorCode")
    private String errorCode;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("createdDate")
    private String createdDate;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}

package com.example.yunseung_u.plotchain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Episode {

    @Expose
    @SerializedName("name")
    private String subtitle;
    @Expose
    @SerializedName("content")
    private String main_text;
    @Expose
    @SerializedName("session")
    private String session;
    @Expose
    @SerializedName("createDate")
    private String createData;
    @Expose
    @SerializedName("id")
    private String epsodeid;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getEpsodeid() {
        return epsodeid;
    }

    public void setEpsodeid(String epsodeid) {
        this.epsodeid = epsodeid;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getCreateData() {
        return createData;
    }

    public void setCreateData(String createData) {
        this.createData = createData;
    }

    public String getMain_text() {
        return main_text;
    }

    public void setMain_text(String main_text) {
        this.main_text = main_text;
    }

    public Episode(String subtitle, String main_text) {

        this.subtitle = subtitle;
        this.main_text = main_text;
    }
}

package com.example.yunseung_u.plotchain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    public User(){
    }

    @Expose
    @SerializedName("nickname")
    private String mNickname;
    @Expose
    @SerializedName("email")
    private String mEmail;
    @Expose
    @SerializedName("password")
    private String mPassword;

    @Expose
    @SerializedName("session")
    private String mSession;
    @Expose
    @SerializedName("address")
    private String mAddress;

    public String getmSession() {
        return mSession;
    }

    public void setmSession(String mSession) {
        this.mSession = mSession;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getSession() {
        return mSession;
    }

    public void setSession(String mSession) {
        this.mSession = mSession;
    }

    public User(String mNickname, String mEmail, String mPassword) {
        this.mNickname = mNickname;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmNickname() {
        return mNickname;
    }

    public void setmNickname(String mNickname) {
        this.mNickname = mNickname;
    }
}

package com.crud.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Logintabble {
    @SerializedName("loginId")
    @Expose
    private int LoginId;
    @SerializedName("username")
    @Expose
    private String Username;
    @SerializedName("password")
    @Expose
    private String Password;
    @SerializedName("userEmail")
    @Expose
    private String UserEmail;
    @SerializedName("userPhoneno")
    @Expose
    private String UserPhoneno;
    @SerializedName("name")
    @Expose
    private String Name;

    public int getLoginId() {
        return LoginId;
    }

    public void setLoginId(int loginId) {
        LoginId = loginId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserPhoneno() {
        return UserPhoneno;
    }

    public void setUserPhoneno(String userPhoneno) {
        UserPhoneno = userPhoneno;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

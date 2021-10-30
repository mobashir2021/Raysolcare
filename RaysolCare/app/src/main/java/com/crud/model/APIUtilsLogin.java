package com.crud.model;

public class APIUtilsLogin {

    private APIUtilsLogin(){

    }
    public static final String API_URL = "http://www.raysolcare.com/api/Loginapi/";

    public static LoginService getLoginService(){
        return RetrofitClient.getClient(API_URL).create(LoginService.class);
    }
}

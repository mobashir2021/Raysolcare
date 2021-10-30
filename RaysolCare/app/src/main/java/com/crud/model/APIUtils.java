package com.crud.model;

public class APIUtils {
    private APIUtils(){

    }

    public static final String API_URL = "http://www.raysolcare.com/api/Servicesapi/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(API_URL).create(UserService.class);
    }
}

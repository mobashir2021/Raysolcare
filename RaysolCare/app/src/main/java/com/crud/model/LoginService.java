package com.crud.model;

import com.crud.entities.Logintabble;
import com.crud.entities.Logintabbles;
import com.crud.entities.Servicesvm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginService {
    @GET("findLogin/{UserPhoneno}")
    Call<Logintabble> findLogin(@Query("UserPhoneno") String UserPhoneno);

    @GET("findLoginbyPassword")
    Call<Logintabble> findLoginbyPassword(@Query("Username") String Username, @Query("Password") String Password);

    @POST("/")
    Call<Void> addlogin(@Body Logintabble logintabble);
}

package com.crud.model;
import com.crud.entities.Servicesvm;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {

    @GET("findBooking")
    Call<List<Servicesvm>> findBooking(@Query("userid") int userid);

    @POST("/")
    Call<Void> addservice(@Body Servicesvm servicesvm);
}

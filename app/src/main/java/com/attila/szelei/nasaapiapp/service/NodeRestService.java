package com.attila.szelei.nasaapiapp.service;

import com.attila.szelei.nasaapiapp.model.ApodPictures;
import com.attila.szelei.nasaapiapp.model.LoginRequest;
import com.attila.szelei.nasaapiapp.model.LoginResponse;
import com.attila.szelei.nasaapiapp.model.MarsRoverPOJO;
import com.attila.szelei.nasaapiapp.model.NasaApiList;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NodeRestService {

    @GET("/apod") //localhost:3000/pictures/apod_image --> apod_image is an endpoint
    Call<ApodPictures> getAPODpicture();

    @GET ("/marsRover")
    Call<List<MarsRoverPOJO.Photo>> getRoverData();

    @POST("user/signup")
    @FormUrlEncoded
    Observable<String>signup(@Field("email") String email, @Field("password") String password);

    @POST("user/login")
    Call<LoginResponse>login(@Body LoginRequest loginRequest);

    @GET("/nasaApiList")
    Call<List<NasaApiList>> getnasaApiList();
}

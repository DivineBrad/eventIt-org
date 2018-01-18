package com.example.bradj.eventitorg.Model.Service;

import com.example.bradj.eventitorg.Model.Entity.RegisteredEvent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Bradley Blanchard on 2018-01-06.
 */

public interface RegisteredEventService {
    @GET("api/registration")
    @Headers("Content-Type: application/json;charset=UTF-8")
    Call<List<RegisteredEvent>> getRegisteredEvents();
    @GET("api/registration/user/{id}")
    @Headers("Content-Type: application/json;charset=UTF-8")
    Call<List<RegisteredEvent>> getUserRegisteredEvents(@Path("id") long id);
    @POST("api/registration")
    @Headers("Content-Type: application/json;charset=UTF-8")
    Call<RegisteredEvent> addRegisteredEvent(@Body RegisteredEvent regEvent);
    @GET("api/registration/checked/{regEveId}")
    @Headers("Content-Type: application/json;charset=UTF-8")
    Call<RegisteredEvent> updateRegisteredEvent(@Path("regEveId") int regEveId);
    @DELETE("api/registration/{id}")
    @Headers("Content-Type: application/json;charset=UTF-8")
    Call<RegisteredEvent> deleteRegisteredEvent(@Path("id") long id);
    @GET("api/registration/checked/{id}")
    @Headers("Content-Type: application/json;charset=UTF-8")
    Call<RegisteredEvent> checkinUser(@Path("id") long id);



}

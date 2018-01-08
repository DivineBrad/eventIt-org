package com.example.bradj.eventitorg.Model.Service;


import com.example.bradj.eventitorg.Model.Entity.OrgUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ajibd on 1/3/2018.
 */

public interface UserService {

//    @POST("/api/user")
//    @FormUrlEncoded
//    Call<User> savePost(@Field("userName") String userName,
//                        @Field("pass") String pass,
//                        @Field("email") String email,
//                        @Field("firstName") String firstName,
//                        @Field("lastName") String lastName
//                        );

    @POST("/api/org-user")
    Call<OrgUser> saveUser(@Body OrgUser user);


//    @GET("/api/org-user")
//    Call<UserResponse> getUsers();

    @GET("/api/org-user/{userId}")
    Call<OrgUser> getUser(@Path("userId") String userId);

    @POST("/api/org-user/login")
    Call<OrgUser> getLoginUser(@Body OrgUser user);
//
//    @GET("/api/event")
//    Call<List<Event>> getEvents();
}

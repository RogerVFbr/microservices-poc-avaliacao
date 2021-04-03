package com.soundlab.template.external.user;

import com.soundlab.template.external.user.models.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IUserApi {
    @GET("/users")
    Call<List<UserResponse>> getUsers();

    @GET("/users/{id}")
    Call<UserResponse> getUsersById(@Path("id") Long id);
}

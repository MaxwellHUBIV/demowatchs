package com.example.demowatchs;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("auth/login")
    Call<com.example.demowatchs.LoginResponse> userLogin(@Body com.example.demowatchs.LoginRequest loginRequest);

}
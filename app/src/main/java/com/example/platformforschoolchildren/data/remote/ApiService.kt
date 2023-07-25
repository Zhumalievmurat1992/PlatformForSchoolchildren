package com.example.platformforschoolchildren.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import com.example.platformforschoolchildren.data.remote.model.RegisterDto

interface ApiService {

    @POST("auth/registration")
    suspend fun registerUser(
        @Body reg: RegisterDto?
    ): Response<RegisterDto?>

    @POST("auth/fillingUserForm")
    suspend fun registerUser2Step(
        @Body reg: RegisterDto?
    ): Response<RegisterDto?>

}
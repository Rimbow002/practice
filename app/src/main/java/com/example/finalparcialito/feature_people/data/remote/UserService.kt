package com.example.finalparcialito.feature_people.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("/api/")
    fun getAll(@Query("results") results: Int): Call<ApiResponse>
}
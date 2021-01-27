package com.example.kotlin_mvvm.network

import com.example.kotlin_mvvm.model.LoginResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BackEndApi {

    @Headers("AUTH_KEY: 27375a7bf4992af9394d3dabb74acc14")
    @GET("MobileAppLogin?")
    fun doLogin(
        @Query("USERCODE") USERCODE: String,
        @Query("PASSWORD") PASSWORD: String,
        @Query("ACTION") ACTION: Int
    ): Call<LoginResponseModel>
}
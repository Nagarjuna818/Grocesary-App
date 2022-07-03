package com.example.grocessaryapp.network

import com.example.grocessaryapp.models.LoginResponse
import com.example.grocessaryapp.models.RegisterResponse
import com.example.grocessaryapp.models.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface MyApi {

    @POST("auth/register")
    fun register(@Body user: User): Call<RegisterResponse>

    @POST("auth/login")
    fun login(@Body login: User): Call<LoginResponse>

    companion object {
        operator fun invoke(): MyApi {
            return Retrofit.Builder()
                .baseUrl("http://apolis-grocery.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}
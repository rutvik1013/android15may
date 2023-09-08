package com.example.retrofitregastrationlogin

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Apiinterface
{
    @FormUrlEncoded
    @POST("register_retro.php")
    fun register(@Field("name") name:String?,@Field("email") email:String?,@Field("password") password:String?):Call<Void>

    @FormUrlEncoded
    @POST("login_retro.php")
    fun login(@Field("email") email: String?,@Field("password") password: String?):Call<Model>
}
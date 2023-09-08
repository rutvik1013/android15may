package com.example.retrofitcrud

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Apiinterface
{
    @FormUrlEncoded
    @POST(" insert_retro.php")
    fun insertdata(@Field("fullname") fullname:String,@Field("email") email:String,@Field("password") password:String,

                   ):Call<Void>

    @GET("view_retro.php")
    fun viewdata():Call<List<Model>>

    @FormUrlEncoded
    @POST("update_retro.php")
    fun updatedata(@Field("id") id:Int,@Field("fullname") fullname: String,@Field("email") email: String,@Field("password") password: String,

    ):Call<Void>

    @FormUrlEncoded
    @POST(" delete_retro.php")
    fun deletedata(@Field("id") id: Int):Call<Void?>?

}
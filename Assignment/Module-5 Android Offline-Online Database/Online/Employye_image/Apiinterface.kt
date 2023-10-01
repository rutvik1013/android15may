package com.example.empassign

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface Apiinterface
{
    @Multipart
    @POST("uploadimage.php")
    suspend fun uploaddata(@Part image:MultipartBody.Part,
                           @Part("efirstname") efirstname:RequestBody?,
                           @Part("elastname") elastname:RequestBody?,
                           @Part("e_experience") e_experience:RequestBody?,
                           @Part("email") email:RequestBody?,
                           @Part("password") password:RequestBody?,
                           @Part("mobile") mobile:RequestBody?,):ResponseBody
    @FormUrlEncoded
    @POST("login.php")
    fun logindata(@Field("email")email:String,@Field("password") password:String,)

    @GET("view_emp_details.php")
    fun getdata():Call<List<Model>>

    @FormUrlEncoded
    @POST("update_emp_details.php")
    fun updatedata(@Field("id") id:Int,
                   @Field("efirstname") efirstname:String,
                   @Field("elastname") elastname:String,
                   @Field("e_experience") e_experience:String,
                   @Field("email") email:String,
                   @Field("password") password:String,
                   @Field("mobile") mobile:String,):Call<Void>

    @FormUrlEncoded
    @POST("delete_emp_details.php")
    fun deletedata(@Field("id") id:Int):Call<Void>


}
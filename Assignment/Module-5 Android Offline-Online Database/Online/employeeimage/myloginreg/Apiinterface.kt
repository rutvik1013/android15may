package com.example.myloginreg

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface Apiinterface {
    @Multipart
    @POST("uploadimage.php")
    fun uploaddata(
        @Part("efirstname") efirstname: MultipartBody.Part,
        @Part("elastname") elastname: RequestBody?,
        @Part("e_experience") e_experience: RequestBody?,
        @Part("email") email: RequestBody?,
        @Part("password") password:RequestBody?,
        @Part("mobile") mobile:RequestBody?,
        @Part file: RequestBody
    ): Call<ResponseBody?>?
}
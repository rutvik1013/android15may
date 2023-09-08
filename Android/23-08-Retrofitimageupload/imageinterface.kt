package com.example.imageupload

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface imageinterface
{
        @FormUrlEncoded
        @POST("upload_prectice.php")
        fun getuserlogin(
            @Field("name") name:String?,
            @Field("url") url:String?,

        ):Call<String?>?

        companion object
        {
            const val IMAGEURL="https://rutvikbabariya.000webhostapp.com/image/"
        }
}
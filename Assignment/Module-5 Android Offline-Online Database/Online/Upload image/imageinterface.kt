package com.example.empprofile


import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface imageinterface {
    @FormUrlEncoded
    @POST("upload_Assign.php")
    fun getUserLogin(
        @Field("name") name: String?,
        @Field("url") url: String?

    ): Call<String?>?

    companion object {
        const val imageurl = "https://rutvikbabariya.000webhostapp.com/image/"
    }
}


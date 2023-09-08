package com.example.retrofitregastrationlogin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient
{
    companion object{
        lateinit var retrofit: Retrofit
        var BASE_URL="https://rutvikbabariya.000webhostapp.com/API2/"
        fun getapiclient():Retrofit
        {
            retrofit=Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit
        }
    }
}
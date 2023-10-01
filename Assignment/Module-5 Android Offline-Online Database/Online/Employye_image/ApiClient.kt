package com.example.empassign

import androidx.core.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient
{
    companion object
    {
        var base_url="https://rutvikbabariya.000webhostapp.com/API2/"
        lateinit var retrofit: Retrofit

        fun getapiclient():Retrofit
        {
            retrofit=Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit

        }
    }
}
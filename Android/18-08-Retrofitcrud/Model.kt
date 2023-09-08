package com.example.retrofitcrud

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Model
{
    @Expose
    @SerializedName("id")
    var id=0

    @Expose
    @SerializedName("fullname")
    var fullname:String?=null

    @Expose
    @SerializedName("email")
    var email:String?=null

    @Expose
    @SerializedName("password")
    var password:String?=null


}
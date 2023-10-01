package com.example.empassign

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Model
{
    @Expose
    @SerializedName("id")
    var id=0

    @Expose
    @SerializedName("efirstname")
    var efirstname:String?=null

    @Expose
    @SerializedName("elastname")
    var elastname:String?=null

    @Expose
    @SerializedName("e_experience")
    var e_experience:String?=null

    @Expose
    @SerializedName("email")
    var email:String?=null

    @Expose
    @SerializedName("password")
    var password:String?=null

    @Expose
    @SerializedName("mobile")
    var mobile:String?=null

    @Expose
    @SerializedName("image")
    var image:String?=null
}
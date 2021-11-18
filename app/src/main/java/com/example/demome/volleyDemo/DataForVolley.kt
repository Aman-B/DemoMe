package com.example.demome.volleyDemo

import com.google.gson.annotations.SerializedName

data class DataForVolley (
    @SerializedName("data") val data : Data,
    @SerializedName("support") val support : Support
)


    data class Data(
        @SerializedName("id")  val id : Int,
        @SerializedName("email")val email : String,
        @SerializedName("first_name") val firstName : String,
        @SerializedName("last_name")val lastName : String,
        @SerializedName("avatar") val avatar : String
    )
    data class Support
    (
        @SerializedName("url") val url : String,
        @SerializedName("text")val text : String
    )

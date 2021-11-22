package com.example.demome.volleyDemo

import com.google.gson.annotations.SerializedName
import java.lang.reflect.Constructor

data class UserData(
    @SerializedName("data") val data: Data,
    @SerializedName("support") val support: Support

)


data class Data(
    @SerializedName("id") val id: Int =1,
    @SerializedName("email") val email: String= "dummyemail@email.com",
    @SerializedName("first_name") val firstName: String="FirstDummyName",
    @SerializedName("last_name") val lastName: String="LastDummyName",
    @SerializedName("avatar") val avatar: String="https://reqres.in/img/faces/2-image.jpg"
)

data class Support
    (
    @SerializedName("url") val url: String = "someURL",
    @SerializedName("text") val text: String="Some text for support."
)

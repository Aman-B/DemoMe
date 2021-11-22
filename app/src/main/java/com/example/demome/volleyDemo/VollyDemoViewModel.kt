package com.example.demome.volleyDemo

import android.app.Application
import android.util.Log
import androidx.lifecycle.*

class VollyDemoViewModel(application: Application) : AndroidViewModel(application) {
    var userData: UserData? = null

    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val imageURL = MutableLiveData<String>()

    init {
        name.value = "Loading your name..."
        email.value = "Loading your email.."
        imageURL.value = ""
    }


    fun updateResponseInUI(userData: UserData) {
        email.postValue(userData.data.email)
        name.postValue(userData.data.firstName + " " + userData.data.lastName)
        imageURL.postValue(userData.data.avatar)

    }

    companion object
}
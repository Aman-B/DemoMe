package com.example.demome.login

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

     var user:User

     val emailID = String
     val password = String


    var loginClicked = MutableLiveData<Int>()

    init {
        this.user= User("","")
    }

    fun onLoginBtnClicked(email:String,password:String){
        user.setEmail(email)
        user.setPassword(password)

        if(!user.isDataValid)
        {
            loginClicked.value=0;
        }
        else
        {
            loginClicked.value=1
        }
    }

}
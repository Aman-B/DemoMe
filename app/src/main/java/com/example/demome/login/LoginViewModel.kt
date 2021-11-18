package com.example.demome.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

     var user:User

     val emailID = MutableLiveData<String> ()
     val password = MutableLiveData<String>()


    var isLoginSuccessful = MutableLiveData<Int>()

    init {
        this.user= User("","")
    }

    fun onLoginBtnClicked(){
        user.setEmail(emailID.value.toString())
        user.setPassword(password.value.toString())

        if(!user.isDataValid)
        {
            isLoginSuccessful.value=0;
        }
        else
        {
            isLoginSuccessful.value=1
        }
    }

}
package com.example.demome.login

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.MutableLiveData

//Model for login view

class User (private var email : String , private var password : String) {
    val validEmail : String ="aman.bakshi06@gmail.com"
    val validPassword : String = "123456"

    val isDataValid : Boolean
    get() = checkCredentials()

    private fun checkCredentials(): Boolean {
        return (!TextUtils.isEmpty(getEmail())) //check if email is  empty
                && Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches() //check if email is of valid format
                && getPassword().length>=6 //check password length
                && getEmail().equals(validEmail)
                && getPassword().equals(validPassword)
    }

    fun setEmail(email:String)
    {
        this.email=email
    }

         fun setPassword(password:String)
    {
        this.password=password
    }
    private fun getPassword(): String {
        return password;
    }

    private fun getEmail():String {
        return  email;
    }



}
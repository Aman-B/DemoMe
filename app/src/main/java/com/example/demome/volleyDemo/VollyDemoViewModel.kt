package com.example.demome.volleyDemo

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.*
import androidx.test.core.app.ApplicationProvider
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.demome.R
import com.google.gson.Gson
import org.json.JSONObject

class VollyDemoViewModel(application: Application) :AndroidViewModel(application){
    var dataForVolley: DataForVolley? = null;

    val name = MutableLiveData<String> ()
    val email = MutableLiveData<String> ()
    val imageURL = MutableLiveData<String> ()

    val dataURL : String = "https://reqres.in/api/users/2"
    init {
        name.value="Loading your name..."
        email.value="Loading your email.."
        imageURL.value=""

        getDataFromURL(dataURL)
    }

    private fun convertJsonToData(response: JSONObject): DataForVolley? {
        var gson = Gson();

        var data=gson.fromJson(response.toString(),DataForVolley::class.java)
        return data
    }

    private fun getDataFromURL(url: String) {
        val queue = Volley.newRequestQueue(getApplication())

        var jsonRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            {response->
                Log.i("Volley ", " response "+ response);
                dataForVolley = convertJsonToData(response)

                updateResponseInUI(response)
            },
            {
                Toast.makeText(getApplication(),"That didn't work!", Toast.LENGTH_LONG).show()
                Log.i("Volley ", " error "+it);
            })



        // Add the request to the RequestQueue.
        queue.add(jsonRequest)

    }

    private fun updateResponseInUI(response: JSONObject?) {

        name.value=dataForVolley?.data?.firstName+" "+dataForVolley?.data?.lastName
        email.value=dataForVolley?.data?.email

        imageURL.value= dataForVolley?.data?.avatar


    }

    companion object{
        @JvmStatic @BindingAdapter("bind:imgUrl")
        fun loadImageWithUri(imageView: ImageView, imageURL:String) {


        }

    }
}
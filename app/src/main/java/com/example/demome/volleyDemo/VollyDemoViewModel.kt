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

class VollyDemoViewModel(application: Application) : AndroidViewModel(application) {
    var dataForVolley: DataForVolley? = null;

    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val imageURL = MutableLiveData<String>()

    val dataURL: String = "https://reqres.in/api/users/2"

    init {
        name.value = "Loading your name..."
        email.value = "Loading your email.."
        imageURL.value = ""

        //make the call for data
        getDataFromURL(dataURL)
    }


    private fun getDataFromURL(url: String) {
        // create a queue for volley request
        val queue = Volley.newRequestQueue(getApplication())

        // get the json object
        var jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                Log.i("Volley ", " response " + response);

                //convert JSON to our DataForValley class using gson.
                dataForVolley = convertJsonToData(response)

                //update the UI with new data.
                updateResponseInUI(response)
            },
            {
                Toast.makeText(getApplication(), "That didn't work!", Toast.LENGTH_LONG).show()
                Log.i("Volley ", " error " + it);
            })


        // Add the request to the RequestQueue.
        queue.add(jsonRequest)

    }

    //converts JSON to our DataForValley class using gson.
    private fun convertJsonToData(response: JSONObject): DataForVolley? {
        var gson = Gson();

        var data = gson.fromJson(response.toString(), DataForVolley::class.java)
        return data
    }

    private fun updateResponseInUI(response: JSONObject?) {
        name.value = dataForVolley?.data?.firstName + " " + dataForVolley?.data?.lastName

        email.value = dataForVolley?.data?.email

        imageURL.value = dataForVolley?.data?.avatar

    }

    companion object {


    }
}
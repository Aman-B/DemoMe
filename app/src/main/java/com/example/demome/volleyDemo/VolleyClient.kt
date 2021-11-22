package com.example.demome.volleyDemo

import android.content.Context
import android.content.res.Resources
import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.demome.R
import com.example.demome.Utils.Util
import org.json.JSONObject

class VolleyClient : VolleyService{

    override fun getData(requestQueue: RequestQueue,
        userId: String,
        onResponseUpdatedListener: OnResponseUpdatedListener
    ): JSONObject? {

        var userData: JSONObject? = null
        // get the json object

        val jsonRequest =  JsonObjectRequest(
            Request.Method.GET, Util.baseURL+userId, null,
            { response ->


                //convert JSON to our DataForValley class using gson.
                userData=response

                //notify listener for UI changes
                onResponseUpdatedListener.responseUpdated(userData)
            },
            {
                Log.i("Volley ", "error "+ it.networkResponse)
                //some error is there. Create dummy data to show.
                userData=createDummyData()
                onResponseUpdatedListener.responseUpdated(userData)

            })

        requestQueue.add(jsonRequest)
        return userData
    }

    private fun createDummyData(): JSONObject {
        val dummyResponse : JSONObject
        dummyResponse = JSONObject(Util.dummyData)

      return dummyResponse
    }

}
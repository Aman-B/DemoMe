package com.example.demome.volleyDemo

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

object VolleySingleton {


    var requestQueue: RequestQueue? = null

    fun getQueue(context: Context): RequestQueue {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context)
        }
        return requestQueue as RequestQueue
    }


}
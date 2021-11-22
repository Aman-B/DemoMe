package com.example.demome.volleyDemo

import android.content.Context
import com.android.volley.RequestQueue
import org.json.JSONObject

interface VolleyService {

    //service for volley.
    //Not using suspend function here because volley is already non-blocking
    fun getData(requestQueue: RequestQueue, userId:String,onResponseUpdatedListener: OnResponseUpdatedListener): JSONObject?



}
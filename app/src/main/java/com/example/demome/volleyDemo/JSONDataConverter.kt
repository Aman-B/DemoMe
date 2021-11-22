package com.example.demome.volleyDemo

import com.google.gson.Gson
import org.json.JSONObject

class JSONDataConverter {


    //converts JSON to our DataForValley class using gson.
     fun convertJsonToData(response: JSONObject?): UserData? {
        var gson = Gson();
        var data = gson.fromJson(response.toString(), UserData::class.java)
        return data
    }

}
package com.example.demome.volleyDemo

import org.json.JSONObject

interface OnResponseUpdatedListener{
    fun responseUpdated(userJsonObject:JSONObject?)
}
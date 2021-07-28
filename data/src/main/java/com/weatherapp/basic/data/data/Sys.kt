package com.weatherapp.basic.data.data

import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("type")
    val type: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("message")
    val message: Double = 0.0,
    @SerializedName("country")
    val country: String = ""
)
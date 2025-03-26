package com.example.weathernow.data.model

import com.google.gson.annotations.SerializedName

data class RainData(
    @SerializedName("1h")
    val oneHour: Double,
)

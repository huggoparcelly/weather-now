package com.example.weathernow.data.api

import com.example.weathernow.data.model.CurrentWeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherServiceApi {

    @GET("weather")
    suspend fun getCurrentWeatherData(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"): Response<CurrentWeatherData>
}
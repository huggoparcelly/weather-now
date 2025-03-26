package com.example.weathernow.data.api

import com.example.weathernow.data.model.CurrentWeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherServiceApi {

    //    https://api.openweathermap.org/data/2.5/
    //    weather?q={city name}&appid={API key}

    @GET("weather")
    fun getCurrentWeatherData(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"): Call<CurrentWeatherData>
}
package com.example.weathernow.data.api

import com.example.weathernow.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OpenWeatherService {

    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    val instance: OpenWeatherServiceApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(OpenWeatherServiceApi::class.java)

    }
}
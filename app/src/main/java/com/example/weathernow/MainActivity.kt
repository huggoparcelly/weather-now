package com.example.weathernow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.weathernow.data.api.OpenWeatherService
import com.example.weathernow.data.model.CurrentWeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val openWeatherService = OpenWeatherService.instance
        val weatherResponse = openWeatherService
            .getCurrentWeatherData("João Pessoa", "9700368f0776b0ae78a4046c363b9254")

        weatherResponse.enqueue(object : Callback <CurrentWeatherData> {

            override fun onResponse(
                call: Call<CurrentWeatherData?>,
                response: Response<CurrentWeatherData?>
            ) {
                val currentWeatherData = response.body()
                Log.i("Weather", "$currentWeatherData")
            }

            override fun onFailure(
                call: Call<CurrentWeatherData?>,
                t: Throwable
            ) {
                Log.e("Weather", t.message.toString())
            }

        })

    }
}
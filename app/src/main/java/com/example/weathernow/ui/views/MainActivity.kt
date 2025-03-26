package com.example.weathernow.ui.views

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.weathernow.R
import com.example.weathernow.data.api.OpenWeatherService
import com.example.weathernow.data.model.CurrentWeatherData
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val mTextInputLayout: TextInputLayout by lazy { findViewById(R.id.main_text_input_layout)}
    private val mTextField: TextInputEditText by lazy { findViewById(R.id.main_text_field)}
    private val mTvCityName: TextView by lazy { findViewById(R.id.main_tv_city_name)}
    private val mTvCityTemp: TextView by lazy { findViewById(R.id.main_tv_city_temp)}
    private val mTvCityMaxTemp: TextView by lazy { findViewById(R.id.main_tv_city_max_temp)}
    private val mTvCityMinTemp: TextView by lazy { findViewById(R.id.main_tv_city_min_temp)}
    private val mTvCityFeelsTemp: TextView by lazy { findViewById(R.id.main_tv_city_feels_temp)}
    private val mTvCityHumidity: TextView by lazy { findViewById(R.id.main_tv_city_humidity)}

    private val mOpenWeatherService = OpenWeatherService.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTvCityName.text = ""
        mTvCityTemp.text = ""
        mTvCityMaxTemp.text = ""
        mTvCityMinTemp.text = ""
        mTvCityFeelsTemp.text = ""
        mTvCityHumidity.text = ""

        mTextInputLayout.setEndIconOnClickListener {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(mTextField.windowToken, 0)

            val cityName = mTextField.text.toString()

            val weatherResponse = mOpenWeatherService.getCurrentWeatherData(
                cityName,
                OpenWeatherService.API_KEY
            )

            weatherResponse.enqueue(object : Callback <CurrentWeatherData> {

                override fun onResponse(
                    call: Call<CurrentWeatherData?>,
                    response: Response<CurrentWeatherData?>
                ) {
                    if(response.isSuccessful) {
                        val currentWeatherData = response.body()
                        if(currentWeatherData != null) {
                            mTvCityName.text = currentWeatherData.name
                            mTvCityTemp.text = "${currentWeatherData.main.temp}°C"
                            mTvCityMaxTemp.text = "${currentWeatherData.main.tempMax}°C"
                            mTvCityMinTemp.text = "${currentWeatherData.main.tempMin}°C"
                            mTvCityFeelsTemp.text = "${currentWeatherData.main.feelsLike}°C"
                            mTvCityHumidity.text = "${currentWeatherData.main.humidity}%"

                        }
                    } else {
                        MaterialAlertDialogBuilder(this@MainActivity)
                            .setTitle("Ops!")
                            .setMessage("Cidade não encontrada")
                            .setCancelable(false)
                            .setPositiveButton("Ok") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .show()
                    }

                }

                override fun onFailure(
                    call: Call<CurrentWeatherData?>,
                    t: Throwable
                ) {
                    Log.e("Weather Now", "Ocorreu um erro durante a requisição: ${t.message}")
                }

            })


        }


    }
}
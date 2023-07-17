package com.illinodes.temperatura.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.illinodes.temperatura.model.DTO.GetCurrentWeather
import com.illinodes.temperatura.model.DTO.GetWeatherForecast
import com.illinodes.temperatura.model.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherService(): IWeatherService {
    override var currentWeather: MutableLiveData<GetCurrentWeather>? = null
    override var weatherForecast: MutableLiveData<List<GetWeatherForecast>>? = null

    override fun getCurrentWeather(lon: Double, lat: Double, apiKey: String) {
        RetrofitService
            .getService()
            .getCurrentWeather(lon, lat, apiKey)
            .enqueue(object: Callback<GetCurrentWeather>{
                override fun onResponse(
                    call: Call<GetCurrentWeather>,
                    response: Response<GetCurrentWeather>
                ) {
                    currentWeather?.postValue(response.body())
                }

                override fun onFailure(call: Call<GetCurrentWeather>, t: Throwable) {
                    Log.e("[CURRENT_W_ERROR]", t.message.toString())
                }

            })
    }

    override fun getWeatherForecast(
        lon: Double,
        lat: Double,
        numForecastDays: Int,
        apiKey: String
    ) {
        RetrofitService
            .getService()
            .getWeatherForecast(lon, lat, numForecastDays, apiKey)
            .enqueue(object : Callback<GetWeatherForecast>{
                override fun onResponse(
                    call: Call<GetWeatherForecast>,
                    response: Response<GetWeatherForecast>
                ) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<GetWeatherForecast>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}
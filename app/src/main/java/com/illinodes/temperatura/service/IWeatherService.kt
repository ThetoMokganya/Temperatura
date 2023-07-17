package com.illinodes.temperatura.service

import androidx.lifecycle.MutableLiveData
import com.illinodes.temperatura.model.DTO.GetCurrentWeather
import com.illinodes.temperatura.model.DTO.GetWeatherForecast

interface IWeatherService {
    var currentWeather: MutableLiveData<GetCurrentWeather>?
    var weatherForecast: MutableLiveData<List<GetWeatherForecast>>?

    fun getCurrentWeather(lon: Double, lat: Double, apiKey: String)
    fun getWeatherForecast(lon: Double, lat: Double, numForecastDays: Int,  apiKey: String)
}
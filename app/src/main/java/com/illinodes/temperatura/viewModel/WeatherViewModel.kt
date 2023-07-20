package com.illinodes.temperatura.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.illinodes.temperatura.helper.GlobalConstants
import com.illinodes.temperatura.model.DTO.GetCurrentWeather
import com.illinodes.temperatura.model.DTO.GetWeatherForecast
import com.illinodes.temperatura.service.WeatherService

class WeatherViewModel: ViewModel() {

    var currentWeather = MutableLiveData<GetCurrentWeather>()
    var weatherForecast = MutableLiveData<GetWeatherForecast>()
    var units = "metric"
    var exclude = "hourly,current,minutely,alerts"

    private fun onGetCurrentWeatherFactory(successCallback: (GetCurrentWeather) -> Unit): WeatherService.OnGetCurrentWeather{
        return object : WeatherService.OnGetCurrentWeather{
            override fun onSuccess(response: GetCurrentWeather) {
                //TODO: Handling of different HTTP statuses
                successCallback(response)
            }

            override fun onFailure(t: Throwable) {
                Log.e("[CURRENT_WEATHER]", t.message.toString())
                t.printStackTrace()
            }

        }
    }

    fun getCurrentWeather(lon: Double, lat: Double){
        WeatherService.getCurrentWeather(lon, lat, units, GlobalConstants.API_KEY, onGetCurrentWeatherFactory { weather ->
            currentWeather.postValue(weather)
        })
    }

    private fun onGetWeatherForecastFactory(successCallback: (GetWeatherForecast) -> Unit): WeatherService.OnGetForecastWeather{
        return object : WeatherService.OnGetForecastWeather{
            override fun onSuccess(response: GetWeatherForecast) {
                //TODO: Handling of different HTTP statuses
                successCallback(response)
            }

            override fun onFailure(t: Throwable) {
                Log.e("[CURRENT_WEATHER]", t.message.toString())
                t.printStackTrace()
            }

        }
    }

    fun getWeatherForecast(lon: Double, lat: Double){
        WeatherService.getWeatherForecast(
            lon,
            lat,
            5,
            units,
            exclude,
            GlobalConstants.API_KEY,
            onGetWeatherForecastFactory { weather ->
            weatherForecast.postValue(weather)
        })
    }
}
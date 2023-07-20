package com.illinodes.temperatura.model

import com.illinodes.temperatura.model.DTO.GetCurrentWeather
import com.illinodes.temperatura.model.DTO.GetWeatherForecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    //TODO: Use the OneCall api for both current and forecast,
    // for less calls since it contains OneCall contains current weather data
    @GET("2.5/weather/")
    fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("appid") apiKey: String): Call<GetCurrentWeather>

    @GET("3.0/onecall?")
    fun getWeatherForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("cnt") cnt: Int,
        @Query("units") units: String,
        @Query("exclude") exclude: String,
        @Query("appid") apiKey: String
        ): Call<GetWeatherForecast>
}
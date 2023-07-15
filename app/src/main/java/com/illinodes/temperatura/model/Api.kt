package com.illinodes.temperatura.model

import com.illinodes.temperatura.model.DTO.GetCurrentWeather
import com.illinodes.temperatura.model.DTO.GetWeatherForecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("?weather/lat={lat}&lon={lon}&appid={apiKey}")
    fun getCurrentWeather(
        @Path("lat") lat: String,
        @Path("lon") lon: String,
        @Path("apiKey") apiKey: String): Call<GetCurrentWeather>

    @GET("?weather/lat={lat}&lon={lon}&appid={apiKey}")
    fun getWeatherForecast(
        @Path("lat") lat: String,
        @Path("lon") lon: String,
        @Path("apiKey") apiKey: String): Call<GetWeatherForecast>
}
package com.illinodes.temperatura.model.DTO

data class GetWeatherForecast(
    var lat: Double,
    var lon: Double,
    var timezone: String,
    var timezone_offset: Int,
    var daily: List<Daily>
)

data class Daily(
    var dt: Int,
    var sunrise: Int,
    var sunset: Int,
    var moonrise: Int,
    var moonset: Int,
    var moon_phase: Double,
    var summary: String,
    var temp: Temp,
    var feels_like: FeelsLike,
    var pressure: Int,
    var humidity: Int,
    var dew_point: Double,
    var wind_speed: Double,
    var wind_deg: Int,
    var wind_gust: Double,
    var weather: List<WeatherType>,
    var clouds: Int,
    var pop: Double,
    var rain: Double,
    var uvi: Double
)

data class Temp(
    var day: Double,
    var min: Double,
    var max: Double,
    var night: Double,
    var eve: Double,
    var morn: Double
)

data class FeelsLike(
    var day: Double,
    var night: Double,
    var eve: Double,
    var morn: Double
)

data class WeatherType(
    var id: Int,
    var main: String,
    var description: String,
    var icon: String
)

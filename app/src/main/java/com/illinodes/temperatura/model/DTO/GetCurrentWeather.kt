package com.illinodes.temperatura.model.DTO

data class GetCurrentWeather(
    var coord: Coord,
    var weather: List<Weather>,
    var base: String,
    var main: Main,
    var visibility: Int,
    var wind: Wind,
    var clouds: Clouds,
    var dt: Int,
    var sys: Sys,
    var timezone: Int,
    var id: Int,
    var name: String,
    var cod: Int
)

data class Coord(
    var lon: Double,
    var lat: Double
)

data class Weather(
    var id: Int,
    var main: String,
    var description: String,
    var icon: String,
)

data class Main(
    var temp: Float,
    var feels_like: Float,
    var temp_min: Float,
    var temp_max: Float,
    var pressure: Int,
    var humidity: Int
)

data class Wind(
    var speed: Float,
    var deg: Int,
    var gust: Float
)

data class Clouds(
    var all: Int
)

data class Sys(
    var type: Int,
    var id: Int,
    var country: String,
    var sunrise: Int,
    var sunset: Int
)
# Temperatura
Weather app that shows current weather based on your location and as well as weather forecast.

Architecture: The project used a slightly modified MVVM architecture, which uses a Service for 
making backend calls and a viewModel to handle data received from backend and prep it for the View.

API_KEY: Ask the developer for an OpenWeatherMap API_KEY to avoid publicly leaking it on GitHub. Or
create a free tier OpenWeatherMap api for OneCall 3.0 and Current Weather API, then insert it under 
helper/GlobalConstant

Packages: Following are packages used for the project.
-> Retrofit: For making HTTP calls to the web api.
-> Retrofit Gson Converter: To convert JSON objects to Android data objects


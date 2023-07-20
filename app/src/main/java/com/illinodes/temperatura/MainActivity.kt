package com.illinodes.temperatura

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.compose.ui.text.toLowerCase
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.illinodes.temperatura.adapter.ForecastAdapter
import com.illinodes.temperatura.viewModel.WeatherViewModel

class MainActivity : AppCompatActivity(), LocationListener {
    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2
    private var latitude: Double = 0.00
    private var longitude: Double = 0.00
    private var locationCoords = MutableLiveData<Location>()
    private lateinit var weatherVModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getUserLocation()
        init()
        showCurrentWeather()
        switchViews()
        attachRecyclerViews()
    }
    private fun init(){
        weatherVModel = WeatherViewModel()
    }
    private fun attachRecyclerViews(){
        val forecastRV = findViewById<RecyclerView>(R.id.rvForecast)
        forecastRV.layoutManager = LinearLayoutManager(this)

        weatherVModel.weatherForecast.observe(this){
            val adapter = ForecastAdapter(it.daily)
            forecastRV.adapter = adapter
        }
    }
    private fun showCurrentWeather(){
        weatherVModel.currentWeather.observe(this){
            var currentTemp = findViewById<TextView>(R.id.txtCurrentTemp)
            var temp = findViewById<TextView>(R.id.txtTemp)
            var minTemp = findViewById<TextView>(R.id.txtMinTemp)
            var maxTemp = findViewById<TextView>(R.id.txtMaxTemp)
            var txtWeatherCondition = findViewById<TextView>(R.id.txtWeatherCondition)

            currentTemp.text = "${it.main.temp.toInt()}\u00B0"
            temp.text = "${it.main.temp.toInt()}\u00B0"
            minTemp.text = "${it.main.temp_min.toInt()}\u00B0"
            maxTemp.text = "${it.main.temp_max.toInt()}\u00B0"
            txtWeatherCondition.text = it.weather[0].main
        }
    }

    private fun switchViews(){
        weatherVModel.currentWeather.observe(this){
            var weatherImage = findViewById<ImageView>(R.id.weatherImage)
            var bottomLayout = findViewById<ConstraintLayout>(R.id.bottomLayout)
            when(it.weather[0].main.lowercase()){
                "rain" -> {
                    weatherImage.setImageResource(R.drawable.forest_rainy)
                    bottomLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.rainy_forest))
                }
                "clouds" -> {
                    weatherImage.setImageResource(R.drawable.forest_cloudy)
                    bottomLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.cloudy_forest))
                }
                "clear" -> {
                    weatherImage.setImageResource(R.drawable.forest_sunny)
                    bottomLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.sunny_forest))
                }
            }
        }
    }

    private fun getUserLocation(){
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                locationPermissionCode)
        }
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            5000, 5f,
            this)
    }

    override fun onLocationChanged(location: Location) {
        if (location != null){
            weatherVModel.getCurrentWeather(location.longitude, location.latitude)
            weatherVModel.getWeatherForecast(location.longitude, location.latitude)
        }else{
            Log.e("[LOCATION]", "Couldn't get location coordinates")
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
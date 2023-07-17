package com.illinodes.temperatura.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.illinodes.temperatura.R
import com.illinodes.temperatura.model.DTO.GetWeatherForecast

class ForecastAdapter(val context: Context): RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    private val weatherForecastList = mutableListOf<GetWeatherForecast>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(LayoutInflater.from(context).inflate(
            R.layout.item_weather_forecast,
            parent, false))
    }

    override fun getItemCount(): Int {
        return weatherForecastList.size
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class ForecastViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
}
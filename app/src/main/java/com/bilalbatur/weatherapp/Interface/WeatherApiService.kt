package com.bilalbatur.weatherapp.Interface

import com.bilalbatur.weatherapp.Model.Weather
import com.bilalbatur.weatherapp.Model.WeatherResponse
import com.bilalbatur.weatherapp.MyModel.LocationData
import com.bilalbatur.weatherapp.MyModel.UserData
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApiService {
    private val BASE_URL = "http://api.openweathermap.org/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(WeatherService::class.java)

    fun getDataService(lat: String,lon: String,exc: String,aps: String): Single<WeatherResponse> {
        return api.getCurrentWeatherData(LocationData.latitude.toString(),LocationData.longitude.toString(),"daily",UserData.userApiKey.toString())
    }
}
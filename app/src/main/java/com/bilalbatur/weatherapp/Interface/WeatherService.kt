package com.bilalbatur.weatherapp.Interface

import com.bilalbatur.weatherapp.Model.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/onecall?lang=tr&units=metric")
    fun getCurrentWeatherData(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("exclude") exc: String,
        @Query("appid") app_id: String
    ): Single<WeatherResponse>


}
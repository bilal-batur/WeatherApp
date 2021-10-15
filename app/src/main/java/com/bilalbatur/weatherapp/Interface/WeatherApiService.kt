package com.bilalbatur.weatherapp.Interface

import com.bilalbatur.weatherapp.Model.Weather
import com.bilalbatur.weatherapp.Model.WeatherResponse
import com.bilalbatur.weatherapp.MyModel.LocationData
import com.bilalbatur.weatherapp.MyModel.UserData
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class WeatherApiService {
    private val BASE_URL = "http://api.openweathermap.org/"


    private val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor) // same for .addInterceptor(...)
        .connectTimeout(30, TimeUnit.SECONDS) //Backend is really slow
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(WeatherService::class.java)

    fun getDataService(
        lat: String,
        lon: String,
        exc: String,
        aps: String
    ): Single<WeatherResponse> {
        return api.getCurrentWeatherData(
            LocationData.latitude.toString(),
            LocationData.longitude.toString(),
            "alert",
            UserData.userApiKey.toString()
        )
    }
}
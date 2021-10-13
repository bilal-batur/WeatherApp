package com.bilalbatur.weatherapp.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bilalbatur.weatherapp.Interface.WeatherApiService
import com.bilalbatur.weatherapp.Model.WeatherResponse
import com.bilalbatur.weatherapp.MyModel.LocationData
import com.bilalbatur.weatherapp.MyModel.UserData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {
    private val weatherApiService = WeatherApiService()
    private val disposable = CompositeDisposable()
    private  val TAG = "MainViewModel"

    val weatherData = MutableLiveData<WeatherResponse>()
    val weatherError = MutableLiveData<Boolean>()
    val weatherLoad = MutableLiveData<Boolean>()

    fun refreshData(excs: String) {
        getDataFromApi(excs)
    }


    fun getDataFromApi(exc: String) {
        println("loc: ${LocationData.latitude.toString()}")
        println("lat: ${LocationData.longitude.toString()}")
        println("exc: $exc")
        println("loc lat: ${UserData.userApiKey.toString()}")
        weatherLoad.value = true
        disposable.add(
            weatherApiService.getDataService(
                LocationData.latitude.toString(),
                LocationData.longitude.toString(),
                exc,
                UserData.userApiKey.toString()
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeatherResponse>() {


                    override fun onSuccess(t: WeatherResponse) {
                        weatherData.value = t
                        weatherError.value = false
                        weatherLoad.value = false
                    }

                    override fun onError(e: Throwable) {
                        weatherError.value = true
                        weatherLoad.value = false
                        Log.e(TAG, "onError: " + e)
                    }

                })
        )

    }
}
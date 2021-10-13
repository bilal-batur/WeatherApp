package com.bilalbatur.weatherapp.View

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bilalbatur.weatherapp.Interface.WeatherService
import com.bilalbatur.weatherapp.Model.WeatherResponse
import com.bilalbatur.weatherapp.MyModel.LocationData
import com.bilalbatur.weatherapp.MyModel.UserData
import com.bilalbatur.weatherapp.ViewModel.MainViewModel
import com.bilalbatur.weatherapp.databinding.ActivityMainBinding
import com.bilalbatur.weatherapp.databinding.ActivityWeatherPageBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL


class WeatherPage : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherPageBinding;
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.refreshData("hourly");
        getLiveData()


    }

    private fun getLiveData() {
        viewModel.weatherData.observe(this, Observer { myData ->
            myData.let {
                binding.textCity.text = myData.main?.seaLevel.toString()
            }
        })


        viewModel.weatherError.observe(this, Observer { error ->
            error?.let {
                if (error) {
                    println("error = $error")
                } else {
                    println("Hata yok")
                }

            }
        })

        viewModel.weatherLoad.observe(this, Observer { loading ->
            loading?.let {
                if (loading) {
                    binding.textCity.visibility = View.VISIBLE
                    binding.temper.visibility = View.VISIBLE

                } else {
                    binding.textCity.visibility = View.VISIBLE
                    println("Load yok")
                }

            }
        })


    }


}






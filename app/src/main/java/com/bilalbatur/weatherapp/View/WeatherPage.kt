package com.bilalbatur.weatherapp.View

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bilalbatur.weatherapp.Model.WeatherResponse
import com.bilalbatur.weatherapp.R
import com.bilalbatur.weatherapp.View.Adapter.MyAdapter
import com.bilalbatur.weatherapp.ViewModel.MainViewModel
import com.bilalbatur.weatherapp.databinding.ActivityWeatherPageBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class WeatherPage : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherPageBinding;
    private lateinit var viewModel: MainViewModel
    private lateinit var weatherArrayList: ArrayList<WeatherView>
    private val weatherListObservable: LiveData<WeatherResponse>? = null
    private var countryLocation: String? = null;
    private var sdf = SimpleDateFormat("EEEE")
    private var d = Date()
    private var dayOfTheWeek = sdf.format(d)
    private val calendar = Calendar.getInstance()
    private val currentDay = calendar[Calendar.DAY_OF_WEEK]
    private val weekDay = arrayOf(
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday",
    );


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getDataFromApi("alert");

        getLiveData()


    }


    private fun getLiveData() {

        viewModel.weatherData.observe(this, Observer { data ->
            data.let {
                binding.cityText.text =
                    data.timezone.substringAfter("/") + ", " + data.timezone.substringBefore("/")

                Glide.with(this)
                    .load("http://openweathermap.org/img/wn/${data.current.weather[0].icon}@2x.png")
                    .into(binding.iconWeather);


                binding.cityTemp.text =
                    Math.round((data.current.temp)*10/10).toString() + "°C"


                var list = mutableListOf<WeatherView>()
                if (currentDay != 6)
                    for (i in (currentDay + 1)..7)
                        list.add(
                            WeatherView(
                                "${weekDay[i - 1]}",
                                "${Math.round((data.daily[i].temp.max)*10/10)}°C",
                                "${Math.round((data.daily[i].temp.min)*10/10)}°C",
                                "${data.daily[i].weather[0].icon}"
                            )
                        )


                binding.listView.adapter = MyAdapter(this, R.layout.list_item, list)


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
                    binding.cityText.visibility = View.VISIBLE

                } else {
                    binding.cityText.visibility = View.VISIBLE
                    println("Load yok")
                }

            }
        })


    }


}






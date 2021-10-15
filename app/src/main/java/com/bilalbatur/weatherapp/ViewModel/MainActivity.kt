package com.bilalbatur.weatherapp.ViewModel

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.bilalbatur.weatherapp.MyModel.LocationData
import com.bilalbatur.weatherapp.MyModel.UserData
import com.bilalbatur.weatherapp.View.WeatherPage
import com.bilalbatur.weatherapp.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import retrofit2.http.GET
import java.util.EnumSet.of


class MainActivity : AppCompatActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var binding: ActivityMainBinding;


    private val textInputEditText: String
        get() = binding.textInputEditText.text.toString();


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        binding.joinWeather.setOnClickListener() {
            checkLocationPermission()
        }


    }


    fun checkLocationPermission() {
        val lastLocation: Task<Location> = fusedLocationProviderClient.lastLocation
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED

        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
            return
        }

        lastLocation.addOnSuccessListener {
            when (it) {
                null -> Toast.makeText(
                    applicationContext,
                    "Location does not find.",
                    Toast.LENGTH_SHORT
                ).show()
                else -> {
                    LocationData.latitude = it.latitude.toString()
                    LocationData.longitude = it.longitude.toString()
                    UserData.userApiKey = textInputEditText


                    val weatherApi = Intent(this, WeatherPage::class.java)
                    startActivity(weatherApi);
                }
            }
        }

    }


}







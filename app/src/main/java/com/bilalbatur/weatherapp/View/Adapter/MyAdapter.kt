package com.bilalbatur.weatherapp.View.Adapter

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bilalbatur.weatherapp.R
import com.bilalbatur.weatherapp.View.WeatherView
import com.squareup.picasso.Picasso

class MyAdapter(var mtx: Context, var resources:Int ,var items : List<WeatherView>) :
    ArrayAdapter<WeatherView>(
        mtx,
        resources,
        items
    ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(mtx)
        val view: View = inflater.inflate(resources, null);


        val weatherPic: ImageView = view.findViewById(R.id.iconWeathers)
        val dayName: TextView = view.findViewById(R.id.dayNames)
        val maxTemp: TextView = view.findViewById(R.id.tempMax)
        val minTemp: TextView = view.findViewById(R.id.tempMin)


        var mItem:WeatherView = items[position]
        Picasso.with(mtx).load("http://openweathermap.org/img/wn/${mItem.weatherPic}@2x.png").into(weatherPic);
        dayName.text = mItem.daysName
        maxTemp.text = mItem.tempsMax
        minTemp.text = mItem.tempsMin



        return view
    }


}
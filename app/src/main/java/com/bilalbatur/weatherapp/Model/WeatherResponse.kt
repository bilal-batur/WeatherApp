package com.bilalbatur.weatherapp.Model

data class WeatherResponse(
    val current: Current,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    val minutely: List<Minutely>,
    val timezone: String,
    val timezone_offset: Double
)

data class Current(
    val clouds: Double,
    val dew_point: Double,
    val dt: Double,
    val feels_like: Double,
    val humidity: Double,
    val pressure: Double,
    val sunrise: Double,
    val sunset: Double,
    val temp: Double,
    val uvi: Double,
    val visibility: Double,
    val weather: List<Weather>,
    val wind_deg: Double,
    val wind_speed: Double
)

data class Daily(
    val clouds: Double,
    val dew_point: Double,
    val dt: Double,
    val feels_like: FeelsLike,
    val humidity: Double,
    val moon_phase: Double,
    val moonrise: Double,
    val moonset: Double,
    val pop: Double,
    val pressure: Double,
    val rain: Double,
    val sunrise: Double,
    val sunset: Double,
    val temp: Temp,
    val uvi: Double,
    val weather: List<WeatherX>,
    val wind_deg: Double,
    val wind_gust: Double,
    val wind_speed: Double
)

data class Hourly(
    val clouds: Double,
    val dew_point: Double,
    val dt: Double,
    val feels_like: Double,
    val humidity: Double,
    val pop: Double,
    val pressure: Double,
    val temp: Double,
    val uvi: Double,
    val visibility: Double,
    val weather: List<WeatherXX>,
    val wind_deg: Double,
    val wind_gust: Double,
    val wind_speed: Double
)

data class Minutely(
    val dt: Double,
    val precipitation: Double
)

data class Weather(
    val description: String,
    val icon: String,
    val id: Double,
    val main: String
)

data class FeelsLike(
    val day: Double,
    val eve: Double,
    val morn: Double,
    val night: Double
)

data class Temp(
    val day: Double,
    val eve: Double,
    val max: Double,
    val min: Double,
    val morn: Double,
    val night: Double
)

data class WeatherX(
    val description: String,
    val icon: String,
    val id: Double,
    val main: String
)

data class WeatherXX(
    val description: String,
    val icon: String,
    val id: Double,
    val main: String
)








package com.demo.weatherapp.features.home.data.remote

import com.demo.weatherapp.BuildConfig
import com.demo.weatherapp.features.home.data.models.CitiesData
import com.demo.weatherapp.features.home.data.models.CityWeatherDetailsData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(AppUrls.searchCities)
    suspend fun getSearchCities(
        @Query("q") cityName: String? = null,
        @Query("key") appKey: String = BuildConfig.WEATHER_API_KEY
    ): CitiesData

    @GET(AppUrls.cityWetherForcast)
    suspend fun getCityWeatherForcast(
        @Query("q") cityName: String? = null,
        @Query("key") appKey: String = BuildConfig.WEATHER_API_KEY,
        @Query("days") days: String = BuildConfig.FORCAST_DAYS
    ): CityWeatherDetailsData

}
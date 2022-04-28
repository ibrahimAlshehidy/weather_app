package com.demo.weatherapp.features.home.domain

import com.demo.weatherapp.features.home.data.models.CitiesData
import com.demo.weatherapp.features.home.data.models.CityWeatherDetailsData
import com.demo.weatherapp.utils.result.Resource
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getSearchCities(cityName: String?): Flow<Resource<CitiesData>>

    fun getCityWeatherForecast(cityName: String): Flow<Resource<CityWeatherDetailsData>>
}
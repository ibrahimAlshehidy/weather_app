package com.demo.weatherapp.features.home.presentation.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.demo.weatherapp.features.home.data.models.CitiesData
import com.demo.weatherapp.features.home.data.models.CityWeatherDetailsData
import com.demo.weatherapp.features.home.domain.GetCityWeatherForecastUseCase
import com.demo.weatherapp.features.home.domain.GetSearchCitiesUseCase
import com.demo.weatherapp.utils.asMappedResourceLiveData
import com.demo.weatherapp.utils.result.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSearchCitiesUseCase: GetSearchCitiesUseCase,
    private val getCityWeatherForecastUseCase: GetCityWeatherForecastUseCase
) : ViewModel() {

    fun getSearchCities(cityName: String?): LiveData<Resource<List<CitiesData>>> =
        getSearchCitiesUseCase.get(cityName).asMappedResourceLiveData()

    fun getCityWeatherForecast(cityName: String): LiveData<Resource<CityWeatherDetailsData>> =
        getCityWeatherForecastUseCase.get(cityName).asMappedResourceLiveData()

}
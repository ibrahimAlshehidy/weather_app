package com.demo.weatherapp.features.home.domain

import com.demo.weatherapp.features.home.data.models.CitiesData
import com.demo.weatherapp.utils.result.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchCitiesUseCase @Inject constructor(private val mainRepository: MainRepository) {

    fun get(cityName: String?): Flow<Resource<CitiesData>> =
        mainRepository.getSearchCities(cityName)
}
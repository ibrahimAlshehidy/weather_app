package com.demo.weatherapp.features.home.data

import com.demo.weatherapp.features.home.data.models.CitiesData
import com.demo.weatherapp.features.home.data.models.CityWeatherDetailsData
import com.demo.weatherapp.features.home.data.remote.ApiService
import com.demo.weatherapp.features.home.domain.MainRepository
import com.demo.weatherapp.utils.result.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(private val apiService: ApiService) : MainRepository {

    override fun getSearchCities(cityName: String?): Flow<Resource<List<CitiesData>>> {
        return flow {
            emit(Resource.loading())
            try {
                val apiResponse = apiService.getSearchCities(cityName)
                emit(Resource.success(apiResponse))
            } catch (e: IOException) {
                emit(Resource.error())
            }
        }
    }

    override fun getCityWeatherForecast(cityName: String): Flow<Resource<CityWeatherDetailsData>> {
        return flow {
            emit(Resource.loading())
            try {
                val apiResponse = apiService.getCityWeatherForecast(cityName)
                emit(Resource.success(apiResponse))
            } catch (e: IOException) {
                emit(Resource.error())
            }
        }
    }
}

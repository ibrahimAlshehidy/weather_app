package com.demo.weatherapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.demo.weatherapp.utils.result.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart


fun <T> Flow<Resource<T>>.asMappedResourceLiveData(
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
): LiveData<Resource<T>> {
    return onStart { emit(Resource.loading()) }
        .catch {
            emit(Resource.error(it.message))
        }
        .asLiveData(coroutineDispatcher)
}
package com.demo.weatherapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.demo.weatherapp.utils.result.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlin.experimental.ExperimentalTypeInference

fun <T> Flow<T>.asResourceLiveData(
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
): LiveData<Resource<T>> {
    return map {
        if (it is List<*> && it.isEmpty())
            Resource.emptyDataError()
        else {
            Resource.success(it)
        }
    }
        .onStart { emit(Resource.loading()) }
        .catch {
            emit(Resource.error(it.message))
        }
        .asLiveData(coroutineDispatcher)
}

fun <T> Flow<Resource<T>>.asMappedResourceLiveData(
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
): LiveData<Resource<T>> {
    return onStart { emit(Resource.loading()) }
        .catch {
            emit(Resource.error(it.message))
        }
        .asLiveData(coroutineDispatcher)
}

@OptIn(ExperimentalTypeInference::class)
fun <T> mappedLiveData(
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
    @BuilderInference block: suspend LiveDataScope<Resource<T>>.() -> T
): LiveData<Resource<T>> {
    return liveData(coroutineDispatcher) {
        emit(Resource.loading())
        try {
            val data = block()
            emit(Resource.success(data))
        } catch (e: Exception) {
            emit(Resource.error(e.message))
        }
    }
}
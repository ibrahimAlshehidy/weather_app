package com.demo.weatherapp.utils.result

import androidx.annotation.Keep
import com.demo.weatherapp.utils.result.ResourceType.*

@Keep
class Resource<T> {
    val resourceType: ResourceType
    var data: T? = null
        private set
    var message: String? = null
        private set

    constructor(resourceType: ResourceType, data: T?, message: String?) {
        this.resourceType = resourceType
        this.data = data
        this.message = message
    }

    constructor(resourceType: ResourceType, data: T?) {
        this.resourceType = resourceType
        this.data = data
    }

    constructor(resourceType: ResourceType, message: String?) {
        this.resourceType = resourceType
        this.message = message
    }

    companion object {
        fun <T> success(): Resource<T> = Resource(SUCCESS, null)

        fun <T> success(data: T): Resource<T> = Resource(SUCCESS, data)

        fun <T> success(data: T, message: String?): Resource<T> = Resource(SUCCESS, data, message)

        fun <T> successMessage(message: String?): Resource<T> = Resource(SUCCESS, message)

        fun <T> error(message: String?, data: T): Resource<T> = Resource(ERROR, data, message)

        fun <T> error(message: String?): Resource<T> = Resource(ERROR, message)

        fun <T> error(data: T): Resource<T> = Resource(ERROR, data)

        fun <T> error(): Resource<T> = Resource(ERROR, null)

        fun <T> loading(): Resource<T> = Resource(LOADING, null)

        fun <T> loading(data: T): Resource<T> = Resource(LOADING, data)
    }
}
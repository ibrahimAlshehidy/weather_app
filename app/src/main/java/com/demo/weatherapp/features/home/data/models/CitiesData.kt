package com.demo.weatherapp.features.home.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CitiesData(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("region") val region: String?,
    @SerializedName("country") val country: String?,
    @SerializedName("lat") val lat: Double?,
    @SerializedName("lon") val lon: Double?,
    @SerializedName("url") val url: String?,
)

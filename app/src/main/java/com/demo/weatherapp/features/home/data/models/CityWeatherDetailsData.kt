package com.demo.weatherapp.features.home.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CityWeatherDetailsData(
    @SerializedName("location") val location: Location,
    @SerializedName("current") val current: Current,
    @SerializedName("forecast") val forecast: Forecast
) {
    data class Location(
        @SerializedName("name") val name: String?,
        @SerializedName("localtime") val localtime: String?
    )

    data class Current(
        @SerializedName("temp_f") val temp_f: Double?,
        @SerializedName("condition") val condition: Condition,
        @SerializedName("wind_mph") val wind_mph: Double?,
        @SerializedName("humidity") val humidity: Double?
    )

    data class Condition(
        @SerializedName("text") val text: String?,
        @SerializedName("icon") val icon: String?
    )

    data class Forecast(
        @SerializedName("forecastday") val forecastday: List<Forecastday>
    )

    data class Forecastday(
        @SerializedName("date") val date: String?,
        @SerializedName("day") val day: Day
    )

    data class Day(
        @SerializedName("maxtemp_f") val maxtemp_f: Double?,
        @SerializedName("mintemp_f") val mintemp_f: Double?,
        @SerializedName("condition") val condition_day: ConditionDay
    )

    data class ConditionDay(
        @SerializedName("text") val text: String?,
        @SerializedName("icon") val icon: String?
    )
}

package com.team1.covid19info.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class StatisticsCountryDateData(
    @SerializedName("Country")
    val country: String,
    @SerializedName("CountryCode")
    val countryCode: String,
    @SerializedName("Province")
    val province: String,
    @SerializedName("City")
    val city: String,
    @SerializedName("CityCode")
    val cityCode: String,
    @SerializedName("Lat")
    val lat: Double,
    @SerializedName("Lon")
    val lon: Double,
    @SerializedName("Confirmed")
    val confirmed: Long,
    @SerializedName("Deaths")
    val deaths: Long,
    @SerializedName("Recovered")
    val recovered: Long,
    @SerializedName("Active")
    val active: Long,
    @SerializedName("Date")
    val date: Date
)
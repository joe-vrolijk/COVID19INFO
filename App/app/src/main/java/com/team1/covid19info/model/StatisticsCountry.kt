package com.team1.covid19info.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class StatisticsCountry(
    @SerializedName("Country")
    val country: String,
    @SerializedName("CountryCode")
    val countryCode: String,
    @SerializedName("Date")
    val date: Date,
    @SerializedName("NewConfirmed")
    val newConfirmed: Int,
    @SerializedName("TotalConfirmed")
    val totalConfirmed: Int,
    @SerializedName("NewDeaths")
    val newDeaths: Int,
    @SerializedName("TotalDeaths")
    val totalDeaths: Int,
    @SerializedName("NewRecovered")
    val newRecovered: Int,
    @SerializedName("TotalRecovered")
    val totalRecovered: Int
    ) {
    val translatedCountry: String
        get() = Locale(Locale.getDefault().language, countryCode).displayCountry

//    fun getTranslatedCountry(): String {
//        if (_translatedCountry == country)
//            _translatedCountry = Locale(Locale.getDefault().language, countryCode).displayCountry
//
//        return _translatedCountry
//    }
}
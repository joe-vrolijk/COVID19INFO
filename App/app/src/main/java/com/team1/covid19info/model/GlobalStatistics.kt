package com.team1.covid19info.model

import com.google.gson.annotations.SerializedName

data class GlobalStatistics(
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
)
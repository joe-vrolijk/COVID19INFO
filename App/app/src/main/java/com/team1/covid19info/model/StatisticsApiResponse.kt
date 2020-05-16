package com.team1.covid19info.model

import com.google.gson.annotations.SerializedName

data class StatisticsApiResponse (
    @SerializedName("Global")
    val global: GlobalStatistics,
    @SerializedName("Countries")
    val countries: List<StatisticsCountry>
)
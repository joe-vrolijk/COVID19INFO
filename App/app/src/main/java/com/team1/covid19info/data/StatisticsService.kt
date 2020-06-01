package com.team1.covid19info.data

import com.team1.covid19info.model.StatisticsApiResponse
import com.team1.covid19info.model.StatisticsCountryDateData
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path

interface StatisticsService {

    @GET("/summary")
    suspend fun getCovidData(): StatisticsApiResponse

    @GET("/total/country/{country}")
    suspend fun getCountryData(@Path("country") country: String): List<StatisticsCountryDateData>
}
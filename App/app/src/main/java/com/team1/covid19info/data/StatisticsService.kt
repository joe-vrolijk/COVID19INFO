package com.team1.covid19info.data

import com.team1.covid19info.model.StatisticsApiResponse
import retrofit2.http.GET

interface StatisticsService {

    @GET("/summary")
    suspend fun getCovidData(): StatisticsApiResponse

}
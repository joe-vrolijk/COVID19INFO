package com.team1.covid19info.data

import com.google.gson.GsonBuilder
import com.team1.covid19info.config.statisticsApiBaseUrl
import com.team1.covid19info.model.StatisticsApiResponse
import com.team1.covid19info.model.StatisticsCountry
import com.team1.covid19info.util.DateDeserializer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class StatisticsRepository {
    var service: StatisticsService

    init {
    val gsonBuilder = GsonBuilder()
    gsonBuilder.registerTypeAdapter(Date::class.java, DateDeserializer())

    val retrofit = Retrofit.Builder()
        .baseUrl(statisticsApiBaseUrl)
        .addConverterFactory(
            GsonConverterFactory.create(
                gsonBuilder.create()
            ))
        .build()

    service = retrofit.create(StatisticsService::class.java)
}

    suspend fun getCovidData() = service.getCovidData()

    suspend fun getCountryData(country: String) = service.getCountryData(country)
}
package com.team1.covid19info.data

import com.google.gson.GsonBuilder
import com.team1.covid19info.config.newsFeedApiBaseUrl
import com.team1.covid19info.model.NewsFeedApiResponse
import com.team1.covid19info.util.DateDeserializer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class NewsFeedRepository {
    var service: NewsFeedService

    init {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(Date::class.java, DateDeserializer())

        val retrofit = Retrofit.Builder()
            .baseUrl(newsFeedApiBaseUrl)
            .addConverterFactory(
                GsonConverterFactory.create(
                    gsonBuilder.create()
                ))
            .build()

        service = retrofit.create(NewsFeedService::class.java)
    }

    suspend fun getCovidNews() : NewsFeedApiResponse {
        return service.getCovidNews()
    }

}
package com.team1.covid19info.data

import com.team1.covid19info.model.NewsFeedApiResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface NewsFeedService {
    @Headers(
        "x-rapidapi-host: microsoft-azure-bing-news-search-v1.p.rapidapi.com",
        "x-rapidapi-key: 80e88367a2msh89082bc3dac9b8dp1d8c0ejsn685efbc2fe0e"
    )
    @GET("/search?count=20&mkt=nl-NL&q=corona")
    suspend fun getCovidNews(): NewsFeedApiResponse
}
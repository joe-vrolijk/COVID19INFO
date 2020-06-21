package com.team1.covid19info.data

import com.team1.covid19info.model.NewsFeedApiResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface NewsFeedService {
    @Headers(
        "x-rapidapi-host: microsoft-azure-bing-news-search-v1.p.rapidapi.com",
        "x-rapidapi-key: 6dfe5bd618msh63478dc414a7347p1eef4fjsn30e381014e74"
    )
    @GET("/search?count=20&mkt=nl-NL&q=corona")
    suspend fun getCovidNews(): NewsFeedApiResponse
}

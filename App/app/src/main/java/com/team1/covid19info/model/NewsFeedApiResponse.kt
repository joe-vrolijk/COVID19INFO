package com.team1.covid19info.model

import com.google.gson.annotations.SerializedName

data class NewsFeedApiResponse (
    @SerializedName("value")
    val newsItems: List<NewsItem>
)

package com.team1.covid19info.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class NewsItem(
    @SerializedName("name")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("image")
    val image: Image,
    @SerializedName("description")
    val content: String,
    @SerializedName("provider.0.name")
    val provider: String,
    @SerializedName("datePublished")
    val pubDate: Date
    ): Parcelable

@Parcelize
data class Image(
    @SerializedName("thumbnail") var thumbnail: Thumbnail
): Parcelable

@Parcelize
data class Thumbnail(
    @SerializedName("contentUrl") var contentUrl: String
): Parcelable

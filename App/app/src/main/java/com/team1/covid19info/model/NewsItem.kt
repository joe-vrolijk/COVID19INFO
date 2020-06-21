package com.team1.covid19info.model

import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import java.util.*

@IgnoreExtraProperties
class NewsItem {
    @SerializedName("name")
    var title: String? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("image")
    var image: Image? = null
    @SerializedName("description")
    var content: String? = null
    @SerializedName("provider.0.name")
    var provider: String? = null
    @SerializedName("datePublished")
    var pubDate: Date? = null

    constructor() {}

    constructor(
        title: String?,
        url: String?,
        image: Image?,
        content: String?,
        provider: String?,
        pubDate: Date?
    ) {
        this.title = title
        this.url = url
        this.image = image
        this.content = content
        this.provider = provider
        this.pubDate = pubDate
    }

}

class Image {
    @SerializedName("thumbnail")
    var thumbnail: Thumbnail? = null

    constructor() {}

    constructor(thumbnail: Thumbnail?) {
        this.thumbnail = thumbnail
    }
}

class Thumbnail {
    @SerializedName("contentUrl")
    var contentUrl: String? = null

    constructor() {}

    constructor(contentUrl: String?) {
        this.contentUrl = contentUrl
    }
}



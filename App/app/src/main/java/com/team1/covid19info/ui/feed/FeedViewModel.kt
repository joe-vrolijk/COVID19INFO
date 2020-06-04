package com.team1.covid19info.ui.feed

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.team1.covid19info.data.FirebaseDao
import com.team1.covid19info.data.NewsFeedRepository
import com.team1.covid19info.model.NewsItem
import com.team1.covid19info.ui.ViewModelBase
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

class FeedViewModel(context: Context) : ViewModelBase(context) {

    private val newsRepository = NewsFeedRepository()
    private val firebaseDao = FirebaseDao()

    val newsItems = MutableLiveData<List<NewsItem>>()

    fun getCovidNews(){
        val lastUpdated = firebaseDao.getLastUpdateTime()
        val localDateTime = Date.from(Instant.now().minus(1, ChronoUnit.HOURS))
        if (lastUpdated.isBefore(localDateTime.toInstant())) {
            calls.addLast {
                val response = newsRepository.getCovidNews()
                firebaseDao.refreshNewsItems(response.newsItems)
                newsItems.postValue(response.newsItems)
            }
        } else {
            val retrievedNewsItems = firebaseDao.getNewsItems()
            newsItems.postValue(retrievedNewsItems)
        }
    }
}


//calls.addLast {
//    val response = newsRepository.getCovidNews()
//    newsItems.postValue(response.newsItems)
//}

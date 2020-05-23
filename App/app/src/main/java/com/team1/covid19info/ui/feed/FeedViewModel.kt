package com.team1.covid19info.ui.feed

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.team1.covid19info.data.NewsFeedRepository
import com.team1.covid19info.model.NewsItem
import com.team1.covid19info.ui.ViewModelBase

class FeedViewModel(context: Context) : ViewModelBase(context) {

    private val newsRepository = NewsFeedRepository()
    val newsItems = MutableLiveData<List<NewsItem>>()

    fun getCovidNews(){
        calls.addLast {
            val response = newsRepository.getCovidNews()
            newsItems.postValue(response.newsItems)
        }
    }
}

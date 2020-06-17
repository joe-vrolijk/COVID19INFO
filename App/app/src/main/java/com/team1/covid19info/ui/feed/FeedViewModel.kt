package com.team1.covid19info.ui.feed

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.team1.covid19info.data.NewsFeedRepository
import com.team1.covid19info.model.NewsItem
import com.team1.covid19info.ui.ViewModelBase
import kotlinx.coroutines.delay
import java.time.Instant
import java.time.temporal.ChronoUnit


class FeedViewModel(context: Context) : ViewModelBase(context) {

    private val newsRepository = NewsFeedRepository()
    private val newsCollection: MutableList<NewsItem> = mutableListOf()
    private var database: DatabaseReference? = null
    private var lastUpdatedReference: DatabaseReference? = null
    private var newsItemsReference: DatabaseReference? = null

    val newsItems = MutableLiveData<List<NewsItem>>()

    init {
        database = FirebaseDatabase.getInstance().reference
        lastUpdatedReference = FirebaseDatabase.getInstance().getReference("lastUpdated")
        newsItemsReference = FirebaseDatabase.getInstance().getReference("newsItems")
    }

    fun getDbNewsItems(){
        newsItemsReference!!.child("newsItems")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Log.e("*** FAILURE RETRIEVING FROM DB ***", "Error on: FeedViewModel::getDBNewsItems")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    p0.children.mapNotNullTo(newsCollection) {
                        it.getValue<NewsItem>(NewsItem::class.java)
                    }
                    newsItems.postValue(newsCollection)
                    Log.i("*** SUCCESS RETRIEVING FROM DB ***", "Retrieval: " + Instant.now().toString())
                }
            })
    }

// runs on (x) amount of minutes
    fun refreshNewsItems() {
        newsItemsReference!!.removeValue()
        calls.addLast {
            val response = newsRepository.getCovidNews()
            newsItemsReference!!.setValue(response).addOnSuccessListener {
                Thread.sleep(1000)
                Log.i("*** API CALL SUCCESS ***", "Retrieval: " + Instant.now().toString())
                getDbNewsItems()
            }
        }
    }
}

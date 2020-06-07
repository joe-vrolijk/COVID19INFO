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

    private lateinit var instantReference: DatabaseReference
    private var instantListener: ValueEventListener? = null
    private var lastUpdated: Long = 0

    val newsItems = MutableLiveData<List<NewsItem>>()

    init {
        database = FirebaseDatabase.getInstance().reference
        lastUpdatedReference = FirebaseDatabase.getInstance().getReference("lastUpdated")
        newsItemsReference = FirebaseDatabase.getInstance().getReference("newsItems")
    }


    fun getCovidNews(){
        getLastUpdateTime()
        val instant = Instant.ofEpochSecond(lastUpdated)
        if (instant.isBefore(Instant.now().minus(1, ChronoUnit.HOURS))) {
            refreshNewsItems()
            Log.i("** DATA REFRESH CALLED **", "INSTANT: " + instant.toString())
        } else {
            Log.i("** DATA REFRESH NOT CALLED **", "DATA IS NOT OLDER THAN 1 HOUR")
        }
    }

    fun insertInstant() {
        lastUpdatedReference!!.removeValue()
        val firstDateTime = ServerValue.TIMESTAMP
        lastUpdatedReference!!.child("instant").child("instant").setValue(firstDateTime)
    }

    fun getLastUpdateTime(){
        lastUpdatedReference!!.child("instant")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Log.i("** ISSUE RETRIEVING LAST UPDATE TIME **","PLEASE INVESTIGATE")
            }

                override fun onDataChange(p0: DataSnapshot) {
                    val tmp = p0.child("instant")
                    lastUpdated = tmp.getValue(Long::class.java)!!
                    Log.i("** RETRIEVAL LAST UPDATED TIME SUCCESS **", Instant.ofEpochMilli(lastUpdated).toString())
            }
            })

    }

    fun getDbNewsItems(){
        newsItemsReference!!.child("newsItems")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Log.i("** NEWS UPDATE FAILED **", "PLEASE INVESTIGATE")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    Log.i("** NEWS DB UPDATE CALLED **", "DATA COUNT NEWS ITEMS: " + p0.childrenCount)
                    p0.children.mapNotNullTo(newsCollection) {
                        it.getValue<NewsItem>(NewsItem::class.java)
                    }
                    newsItems.postValue(newsCollection)
                    Log.i("** NEWS DB UPDATE COMPLETED  **", "DATA COUNT NEWS ITEMS: " + p0.childrenCount)
                }
            })
    }


    fun refreshNewsItems() {
        newsItemsReference!!.removeValue()
        calls.addLast {
            val response = newsRepository.getCovidNews()
            newsItemsReference!!.setValue(response).addOnSuccessListener {
                Log.i("** API CALL SUCCESS **", "Updating NewsItems was successful")
                insertInstant()
                Log.i("** INSERT LASTUPDATE TIME SUCCESS **", "UPDATED TIME IN DB")
            }
            newsItems.postValue(response.newsItems)
        }
    }

}

// LOGIC:
//get last datetime from fb
// if last datetime < now - hour then call api
// then: clear old newsitems, store newsItems to fb, and add to mutablelist and insert new datetime
// else: clear old newsitems, retrieve newsItems in fb and add to mutableList


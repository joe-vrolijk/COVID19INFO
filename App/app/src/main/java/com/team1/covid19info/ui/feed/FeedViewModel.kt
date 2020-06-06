package com.team1.covid19info.ui.feed

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.team1.covid19info.data.NewsFeedRepository
import com.team1.covid19info.model.NewsItem
import com.team1.covid19info.ui.ViewModelBase
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
    private var lastUpdated: Long? = null

    val newsItems = MutableLiveData<List<NewsItem>>()

    init {
        database = FirebaseDatabase.getInstance().reference
        lastUpdatedReference = FirebaseDatabase.getInstance().getReference("lastUpdated")
        newsItemsReference = FirebaseDatabase.getInstance().getReference("newsItems")
    }


    fun getCovidNews(){
        insertInstant()
        getLastUpdateTime()


//        val instant = Instant.ofEpochSecond(lastUpdated!!)
//        if (instant.isBefore(Instant.now().minus(1, ChronoUnit.HOURS))) {
//            refreshNewsItems()
//            Log.i("************", "INSTANT: " + instant.toString())
//        } else {
            getDbNewsItems()
//            Log.i("************", "KISS MY ASS!!!")
//        }
    }

    fun insertInstant() {
        lastUpdatedReference!!.removeValue()
        val firstDateTime = ServerValue.TIMESTAMP
        lastUpdatedReference!!.child("instant").setValue(firstDateTime)
    }

    fun getLastUpdateTime(){
        lastUpdatedReference!!.child("instant")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Log.i("Did not work", "Retrieving LastUpdate")
            }

                override fun onDataChange(p0: DataSnapshot) {
                    val tmp = p0.getValue(Long::class.java)
                    Log.i(" ********  LUI: ", tmp.toString())
                    lastUpdated = tmp
            }
            })

    }

    fun getDbNewsItems(){
        newsItemsReference!!.child("newsItems")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Log.i("NEWSS", "$$$$$$$$$$$$$$$$$$$ NO WORKY")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    Log.i("NEWSS", "$$$$$$$$$$$$$$$$$$$ ::: " + p0.childrenCount)
                    p0.children.mapNotNullTo(newsCollection) {
                        it.getValue<NewsItem>(NewsItem::class.java)
                    }
                    newsItems.postValue(newsCollection)
                }
            })
    }


    fun refreshNewsItems() {
        newsItemsReference!!.removeValue()
        calls.addLast {
            val response = newsRepository.getCovidNews()
            newsItemsReference!!.setValue(response).addOnSuccessListener {
                Log.i("Update Success", "Updating NewsItems was successful")
                insertInstant()
            }
            newsItems.postValue(response.newsItems)
        }
    }

}


//calls.addLast {
//    val response = newsRepository.getCovidNews()
//    newsItems.postValue(response.newsItems)
//}


//get last datetime from fb
// if last datetime < now - hour then call api
// then: clear old newsitems, store newsItems to fb, and add to mutablelist and insert new datetime
// else: clear old newsitems, retrieve newsItems in fb and add to mutableList


package com.team1.covid19info.ui.feed

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.team1.covid19info.data.FirebaseDao
import com.team1.covid19info.data.NewsFeedRepository
import com.team1.covid19info.model.NewsItem
import com.team1.covid19info.ui.ViewModelBase
import kotlinx.coroutines.withContext
import java.sql.Timestamp
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

class FeedViewModel(context: Context) : ViewModelBase(context) {

    private val newsRepository = NewsFeedRepository()
    val database = FirebaseDatabase.getInstance()
    val newsItemsCollection = database.getReference("newsItems")
    val lastUpdatedCollection = database.getReference("lastUpdated")

    private lateinit var instantReference: DatabaseReference
    private var instantListener: ValueEventListener? = null
    private var lastUpdated: Instant? = null

    val newsItems = MutableLiveData<List<NewsItem>>()

    fun getCovidNews(){
        getLastUpdateTime()
        if (lastUpdated!!.isBefore(Instant.now().minus(1, ChronoUnit.HOURS))){
            refreshNewsItems()
        } else {
            getDbNewsItems()
        }
    }


    fun insertInstant() {
        lastUpdatedCollection.removeValue()
        val firstDateTime = Instant.now()
        lastUpdatedCollection.setValue(firstDateTime)
    }


    fun getLastUpdateTime(){

        val ref = lastUpdatedCollection
        val instantRef = ref.child("lastUpdated")
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val instantVal = dataSnapshot.child("lastUpdated").getValue(Instant::class.java)
                val instantChild = dataSnapshot.child("nano").getValue(Long::class.java)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d(TAG, databaseError.getMessage()) //Don't ignore errors!
            }
        }

        instantRef.addListenerForSingleValueEvent(valueEventListener);



//
//        val instantListenerObject = object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val instant = dataSnapshot.getValue<Instant>()
//                lastUpdated = instant
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "JoeRene", databaseError.toException())
//            }
//        }
//        instantReference.addValueEventListener(instantListenerObject)
//        this.instantListener = instantListenerObject
    }

    fun getDbNewsItems(){
        newsItemsCollection.child("newsItems")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    newsItems.postValue(p0.value as List<NewsItem>)
                }
            })
    }


    fun refreshNewsItems() {
        newsItemsCollection.removeValue()
        calls.addLast {
            val response = newsRepository.getCovidNews()
            newsItemsCollection.setValue(response).addOnSuccessListener {
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


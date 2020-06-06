package com.team1.covid19info.data


import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.team1.covid19info.model.NewsItem
import java.sql.Time
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit


class FirebaseDao {


    val database = FirebaseDatabase.getInstance()
    val newsItemsCollection = database.getReference("newsItems")
    val lastUpdatedCollection = database.getReference("lastUpdated")


    private lateinit var timestampReference: DatabaseReference
    private var timestampListener: ValueEventListener? = null
    private var lastUpdated: Timestamp? = null


    fun refreshNewsItems(newsItems: List<NewsItem>) {
        newsItemsCollection.removeValue()
        newsItemsCollection.setValue(newsItems).addOnSuccessListener {
            Log.i("UPDATED", "NewsItems have been updated!")
        }
    }


    fun addFirstTimestamp() {
        val firstDateTime = Timestamp.from(Instant.now().minus(1, ChronoUnit.HOURS))
        lastUpdatedCollection.setValue(firstDateTime)
    }


    fun getLastUpdateTime(){
        lastUpdatedCollection.child("lastUpdated")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    lastUpdated = p0.value as Timestamp
                }
            })
    }

    fun getNewsItems(): List<NewsItem> {
        var newsItems: List<NewsItem> = arrayListOf()
        newsItemsCollection.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.w("NewsItems could not be loaded from the DB", p0.toString())
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val retrievedNewsItems = dataSnapshot.getValue<List<NewsItem>>()
                newsItems = retrievedNewsItems!!
            }
        })
        return newsItems
    }
}




//get last datetime from fb
// if last datetime < now - hour then call api
// then: clear old newsitems, store newsItems to fb, and add to mutablelist and insert new datetime
// else: clear old newsitems, retrieve newsItems in fb and add to mutableList


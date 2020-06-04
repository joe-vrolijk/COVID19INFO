package com.team1.covid19info.data


import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.team1.covid19info.model.NewsItem
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit


class FirebaseDao {
    val database = FirebaseDatabase.getInstance()
    val newsItemsCollection = database.getReference("newsItems")
    val lastUpdatedCollection = database.getReference("lastUpdated")


    fun refreshNewsItems(newsItems: List<NewsItem>) {
        newsItemsCollection.removeValue()
        newsItemsCollection.setValue(newsItems).addOnSuccessListener {
            Log.i("UPDATED", "NewsItems have been updated!")
        }
    }


    fun addFirstInstant() {
        val firstDateTime = LocalDateTime.now().minus(1, ChronoUnit.HOURS)
        lastUpdatedCollection.setValue(firstDateTime)
    }

    fun getLastUpdateTime(): LocalDateTime {
        var instantReturned = LocalDateTime.now()
        lastUpdatedCollection.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                Log.w("LastUpdate could not be read from the DB", p0.toString())
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val lastUpdate = dataSnapshot.getValue<LocalDateTime>()
                if (lastUpdate == null) {
                    addFirstInstant()
                } else {
                    instantReturned = lastUpdate!!
                    Log.d("******", "Value = $lastUpdate")
                }
            }
        })
        return instantReturned
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

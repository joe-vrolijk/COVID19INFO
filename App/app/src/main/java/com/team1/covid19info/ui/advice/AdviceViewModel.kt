package com.team1.covid19info.ui.advice


import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*

import com.team1.covid19info.model.AdviceItem
import com.team1.covid19info.model.AdviceQuestionItem
import com.team1.covid19info.ui.ViewModelBase

class AdviceViewModel(context: Context) : ViewModelBase(context) {
    private var database: DatabaseReference? = null
    private var adviseQuestionReference: DatabaseReference? = null

    var currentAdviceItem = MutableLiveData<AdviceItem>()

    var adviceItems: MutableList<AdviceItem> = mutableListOf()
    // private val itemCollection: MutableList<AdviceItem> = mutableListOf()

    init {
        database = FirebaseDatabase.getInstance().reference
        adviseQuestionReference = FirebaseDatabase.getInstance()
            .getReference("adviceQuestions/questionList")
        }

    fun getQuestionsFromFireBase() {
        adviseQuestionReference!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.mapNotNullTo(adviceItems) {
                  it.getValue<AdviceItem>(AdviceItem::class.java)
                }
                currentAdviceItem.postValue(adviceItems[0])
            }
        })
    }

    fun clickYes() {
        currentAdviceItem.postValue (
            adviceItems[(currentAdviceItem.value as AdviceQuestionItem).linkYesAnswerId!!.toInt()])

    }

    fun clickNo() {
        currentAdviceItem.postValue (
            adviceItems[(currentAdviceItem.value as AdviceQuestionItem).linkNoAnswerId!!.toInt()])
    }

    fun clickReset() {
        currentAdviceItem.postValue (
            adviceItems[0])
    }

}

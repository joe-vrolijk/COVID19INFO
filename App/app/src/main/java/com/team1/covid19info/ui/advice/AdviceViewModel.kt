package com.team1.covid19info.ui.advice


import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.team1.covid19info.model.AdviceAnswerItem

import com.team1.covid19info.model.AdviceItem
import com.team1.covid19info.model.AdviceQuestionItem
import com.team1.covid19info.model.FirebaseAdviceQuestionResponse
import com.team1.covid19info.ui.ViewModelBase

class AdviceViewModel(context: Context) : ViewModelBase(context) {
    private var database: DatabaseReference? = null
    private var adviseQuestionReference: DatabaseReference? = null

    var currentAdviceItem = MutableLiveData<AdviceItem>()

    var adviceItems: ArrayList<AdviceItem> = arrayListOf()

    init {
        database = FirebaseDatabase.getInstance().reference
        adviseQuestionReference = FirebaseDatabase.getInstance()
            .getReference("adviceQuestions/questionList")
        getQuestionsFromFireBase()
    }


    fun getQuestionsFromFireBase() {
        adviseQuestionReference!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val firebaseAdviceQuestionResponses = mutableListOf<FirebaseAdviceQuestionResponse>()
                p0.children.mapNotNullTo(firebaseAdviceQuestionResponses) {
                  it.getValue<FirebaseAdviceQuestionResponse>(FirebaseAdviceQuestionResponse::class.java)
                }
                for (firebaseAdviceQuestionResponse in firebaseAdviceQuestionResponses) {
                    if (firebaseAdviceQuestionResponse.adviceText == null) {
                        adviceItems.add(AdviceQuestionItem(
                            firebaseAdviceQuestionResponse.adviceQuestionId!!,
                            firebaseAdviceQuestionResponse.questionText!!,
                            firebaseAdviceQuestionResponse.linkYesAnswerId!!,
                            firebaseAdviceQuestionResponse.linkNoAnswerId!!
                            ))
                    } else {
                        adviceItems.add(AdviceAnswerItem(
                            firebaseAdviceQuestionResponse.adviceText!!,
                            firebaseAdviceQuestionResponse.adviceQuestionId!!,
                            firebaseAdviceQuestionResponse.questionText!!
                        ))
                    }
                }
                resetAdviseQuestion()
            }
        })
    }

    fun clickYes() {
        currentAdviceItem.postValue (
            adviceItems[(currentAdviceItem.value as AdviceQuestionItem).linkYesAnswerId.toInt()])

    }

    fun clickNo() {
        currentAdviceItem.postValue (
            adviceItems[(currentAdviceItem.value as AdviceQuestionItem).linkNoAnswerId.toInt()])
    }

    fun resetAdviseQuestion() {
        currentAdviceItem.postValue(adviceItems.single{
            it.adviceQuestionId == 0.toLong()
        })
    }

}

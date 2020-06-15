package com.team1.covid19info.model

import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

@IgnoreExtraProperties
open class AdviceItem(adviceQuestionId: Long, questionText:String) {

    @SerializedName("adviceQuestionId")
    var adviceQuestionId: Long? = adviceQuestionId

    @SerializedName("questionText")
    var questionText: String? = questionText
    
//    constructor(adviceQuestionId: Long?, questionText: String?) {
//        this.adviceQuestionId = adviceQuestionId
//        this.questionText = questionText
//    }



}


package com.team1.covid19info.model

import com.google.gson.annotations.SerializedName

class AdviceAnswerItem(adviceQuestionId: Long, questionText: String, adviceText: String) :
    AdviceItem(adviceQuestionId, questionText) {

    @SerializedName("adviceText")
    var adviceText: String? = adviceText

}
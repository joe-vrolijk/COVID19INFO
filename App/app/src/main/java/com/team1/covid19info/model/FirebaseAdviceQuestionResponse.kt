package com.team1.covid19info.model

import com.google.gson.annotations.SerializedName

class FirebaseAdviceQuestionResponse {
    @SerializedName("adviceQuestionId")
    var adviceQuestionId: Long? = null
    @SerializedName("questionText")
    var questionText: String? = null
    @SerializedName("linkYesAnswerId")
    var linkYesAnswerId: Long? = null
    @SerializedName("linkNoAnswerId")
    val linkNoAnswerId: Long? = null
    @SerializedName("adviceText")
    var adviceText: String? = null
}
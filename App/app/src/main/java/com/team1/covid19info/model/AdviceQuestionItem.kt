package com.team1.covid19info.model

import com.google.gson.annotations.SerializedName

class AdviceQuestionItem(
    adviceQuestionId: Long,
    questionText: String,
    linkYesAnswerId: Long,
    linkNoAnswerId: Long
) :
    AdviceItem(adviceQuestionId, questionText) {

    @SerializedName("linkYesAnswerId")
    var linkYesAnswerId: Long? = linkYesAnswerId

    @SerializedName("linkNoAnswerId")
    var linkNoAnswerId: Long? = linkNoAnswerId

}
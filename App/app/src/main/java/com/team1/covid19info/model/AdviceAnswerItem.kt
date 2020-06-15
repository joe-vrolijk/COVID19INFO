package com.team1.covid19info.model

import com.google.gson.annotations.SerializedName

data class AdviceAnswerItem(
    @SerializedName("adviceText")
    val adviceText: String,
    @SerializedName("adviceQuestionId")
    override val adviceQuestionId: Long,
    @SerializedName("questionText")
    override val questionText: String
) :  AdviceItem()
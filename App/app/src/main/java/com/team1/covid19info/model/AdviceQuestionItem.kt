package com.team1.covid19info.model

import com.google.gson.annotations.SerializedName

data class AdviceQuestionItem(
    @SerializedName("adviceQuestionId")
    override val adviceQuestionId: Long,
    @SerializedName("questionText")
    override val questionText: String,
    @SerializedName("linkYesAnswerId")
    val linkYesAnswerId: Long,
    @SerializedName("linkNoAnswerId")
    val linkNoAnswerId: Long
) : AdviceItem()
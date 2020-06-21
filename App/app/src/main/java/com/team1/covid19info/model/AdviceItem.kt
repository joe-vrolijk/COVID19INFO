package com.team1.covid19info.model

import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

@IgnoreExtraProperties
abstract class AdviceItem {
    abstract val adviceQuestionId: Long
    abstract val questionText: String
}


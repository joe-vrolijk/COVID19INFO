package com.team1.covid19info.ui.advice


import androidx.lifecycle.ViewModel
import com.team1.covid19info.data.AdviseDataRepository


class AdviceViewModel : ViewModel() {
    private val repository = AdviseDataRepository()

    val adviseData = repository.getQuestions()
}

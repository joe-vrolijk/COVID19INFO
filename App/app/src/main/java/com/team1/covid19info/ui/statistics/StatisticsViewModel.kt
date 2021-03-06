package com.team1.covid19info.ui.statistics

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.team1.covid19info.data.StatisticsRepository
import com.team1.covid19info.model.GlobalStatistics
import com.team1.covid19info.model.StatisticsCountry
import com.team1.covid19info.model.StatisticsCountryDateData
import com.team1.covid19info.ui.ViewModelBase

class StatisticsViewModel(context: Context) : ViewModelBase(context) {

    private val repository = StatisticsRepository()

    val globalStatistics = MutableLiveData<GlobalStatistics>()
    val countryStatistics = MutableLiveData<List<StatisticsCountry>>()
    val countryDateDataStatistics = MutableLiveData<List<StatisticsCountryDateData>>()

    fun getCovidData() {
        calls.addLast {
            val response = repository.getCovidData()
            globalStatistics.postValue(response.global)
            countryStatistics.postValue(response.countries)
        }
    }

    fun getCountryData(country: String) {
        calls.addLast {
            val response = repository.getCountryData(country)
            countryDateDataStatistics.postValue(response)
        }
    }
}

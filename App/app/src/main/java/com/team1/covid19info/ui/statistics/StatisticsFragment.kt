package com.team1.covid19info.ui.statistics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import com.team1.covid19info.R
import com.team1.covid19info.ui.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.component_covid_data.*

class StatisticsFragment : Fragment() {

    companion object {
        fun newInstance() = StatisticsFragment()
    }

    private lateinit var viewModel: StatisticsViewModel
    private var selectedCountry = MutableLiveData<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = StatisticsViewModel(requireContext())
        viewModel.globalStatistics.observe(viewLifecycleOwner, Observer {
            tvNewConfirmed.text = it.newConfirmed.toString()
            tvTotalConfirmed.text = it.totalConfirmed.toString()
            tvNewDeaths.text = it.newDeaths.toString()
            tvTotalDeaths.text = it.totalDeaths.toString()
            tvNewRecovered.text = it.newRecovered.toString()
            tvTotalRecovered.text = it.totalRecovered.toString()
        })
        viewModel.getCovidData()
        selectedCountry.observe(viewLifecycleOwner, Observer {
            updateTitle()
        })
        selectedCountry.postValue("")
    }

    private fun updateTitle()
    {
        (requireActivity() as MainActivity).setToolbarTitle("${getString(R.string.Bottom_Nav_Statistics)} ${getTitleAppendix()}")
    }

    private fun getTitleAppendix() : String
    {
        return if (selectedCountry.value!!.isNotEmpty()) selectedCountry.value!! else getString(R.string.DefaultStatisticsTitleAppendix)
    }
}

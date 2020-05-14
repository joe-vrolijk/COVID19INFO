package com.team1.covid19info.ui.advice

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.team1.covid19info.R

class AdviceFragment : Fragment() {

    companion object {
        fun newInstance() = AdviceFragment()
    }

    private lateinit var viewModel: AdviceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_advice, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AdviceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

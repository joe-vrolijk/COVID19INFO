package com.team1.covid19info.ui.guidelines

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.team1.covid19info.R

class GuidelinesFragment : Fragment() {

    companion object {
        fun newInstance() = GuidelinesFragment()
    }

    private lateinit var viewModel: GuidelinesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_guidelines, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GuidelinesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

package com.team1.covid19info.ui.advice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.team1.covid19info.R
import kotlinx.android.synthetic.main.fragment_advice.*
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.Observer
import com.team1.covid19info.model.AdviceAnswerItem
import com.team1.covid19info.model.AdviceItem
import com.team1.covid19info.model.AdviceQuestionItem


class AdviceFragment : Fragment() {

    private lateinit var viewModel: AdviceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_advice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnJa.setOnClickListener { viewModel.clickYes() }
        btnNee.setOnClickListener { viewModel.clickNo() }
        btnReset.setOnClickListener { viewModel.resetAdviseQuestion() }

        btnInfo.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.rivm.nl/coronavirus-covid-19/vragen-antwoorden")
            )
            startActivity(browserIntent)
        }
        btnReset.setOnClickListener {
            viewModel.resetAdviseQuestion()
        }
        viewModel.currentAdviceItem.observe(viewLifecycleOwner, Observer {
            tvQuestion.text = it.questionText
            plLoading.visibility = View.GONE
            tvQuestion.visibility = View.VISIBLE
            btnReset.visibility = if (it.adviceQuestionId == 0.toLong() ) View.GONE else View.VISIBLE
            if (it is AdviceQuestionItem) {
                btnJa.visibility = View.VISIBLE
                btnNee.visibility = View.VISIBLE
                tvAdvise.visibility = View.INVISIBLE
                btnInfo.visibility = View.GONE
            } else {
                btnJa.visibility = View.GONE
                btnNee.visibility = View.GONE
                tvAdvise.visibility = View.VISIBLE
                tvAdvise.text = (it as AdviceAnswerItem).adviceText
                btnInfo.visibility = View.VISIBLE
            }
        })
    }
}

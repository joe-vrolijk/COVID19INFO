package com.team1.covid19info.ui.advice

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.team1.covid19info.R
import com.team1.covid19info.data.AdviseDataRepository
import kotlinx.android.synthetic.main.fragment_advice.*

class AdviceFragment : Fragment() {
    companion object {
        fun newInstance() = AdviceFragment()
    }

    private lateinit var viewModel: AdviceViewModel


    private val repo = AdviseDataRepository()
    var currentAdviseQuestion: Int = 0

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvQuestion.text = repo.getQuestionText(currentAdviseQuestion)
        tvAdvise.text = repo.getAdviseText(currentAdviseQuestion)

        if (repo.getAdviseText(currentAdviseQuestion) != null) {
            btnJa.setVisibility(View.GONE)
            btnNee.setVisibility(View.GONE)
        }

        btnJa.setOnClickListener() {
            currentAdviseQuestion = repo.getLinkYesId(currentAdviseQuestion)
            if (repo.isAdvice(currentAdviseQuestion)) {
                btnJa.setVisibility(View.INVISIBLE)
                btnNee.setVisibility(View.INVISIBLE)
                tvAdvise.setVisibility(View.VISIBLE)
                tvQuestion.text = repo.getQuestionText(currentAdviseQuestion)
                tvAdvise.text = repo.getAdviseText(currentAdviseQuestion)
            } else {
                btnJa.setVisibility(View.VISIBLE)
                btnNee.setVisibility(View.VISIBLE)
                tvAdvise.setVisibility(View.INVISIBLE)
                tvQuestion.text = repo.getQuestionText(currentAdviseQuestion)
            }
        }

        btnNee.setOnClickListener() {
            currentAdviseQuestion = repo.getLinkNoId(currentAdviseQuestion)
            if (repo.isAdvice(currentAdviseQuestion)) {
                btnJa.setVisibility(View.INVISIBLE)
                btnNee.setVisibility(View.INVISIBLE)
                tvAdvise.setVisibility(View.VISIBLE)
                tvQuestion.text = repo.getQuestionText(currentAdviseQuestion)
                tvAdvise.text = repo.getAdviseText(currentAdviseQuestion)
            } else {
                btnJa.setVisibility(View.VISIBLE)
                btnNee.setVisibility(View.VISIBLE)
                tvAdvise.setVisibility(View.INVISIBLE)
                tvQuestion.text = repo.getQuestionText(currentAdviseQuestion)
            }
        }

        btnReset.setOnClickListener() {
            currentAdviseQuestion = 0
            btnJa.setVisibility(View.VISIBLE)
            btnNee.setVisibility(View.VISIBLE)
            tvAdvise.setVisibility(View.INVISIBLE)
            tvQuestion.text = repo.getQuestionText(currentAdviseQuestion)
        }
    }
}

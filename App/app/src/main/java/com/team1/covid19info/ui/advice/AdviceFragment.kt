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
        btnReset.visibility = View.INVISIBLE

        if (repo.getAdviseText(currentAdviseQuestion) != null) {
            btnJa.visibility = View.INVISIBLE
            btnNee.visibility = View.INVISIBLE
        }

        btnJa.setOnClickListener() {
            currentAdviseQuestion = repo.getLinkYesId(currentAdviseQuestion)
            if (repo.isAdvice(currentAdviseQuestion)) {
                btnJa.visibility = View.INVISIBLE
                btnNee.visibility = View.INVISIBLE
                tvAdvise.visibility = View.VISIBLE
                tvQuestion.text = repo.getQuestionText(currentAdviseQuestion)
                tvAdvise.text = repo.getAdviseText(currentAdviseQuestion)
            } else {
                btnJa.visibility = View.VISIBLE
                btnNee.visibility = View.VISIBLE
                tvAdvise.visibility = View.INVISIBLE
                tvQuestion.text = repo.getQuestionText(currentAdviseQuestion)
            }
            btnReset.visibility = View.VISIBLE
        }

        btnNee.setOnClickListener() {
            currentAdviseQuestion = repo.getLinkNoId(currentAdviseQuestion)
            if (repo.isAdvice(currentAdviseQuestion)) {
                btnJa.visibility = View.INVISIBLE
                btnNee.visibility = View.INVISIBLE
                tvAdvise.visibility = View.VISIBLE
                tvQuestion.text = repo.getQuestionText(currentAdviseQuestion)
                tvAdvise.text = repo.getAdviseText(currentAdviseQuestion)
            } else {
                btnJa.visibility = View.VISIBLE
                btnNee.visibility = View.VISIBLE
                tvAdvise.visibility = View.INVISIBLE
                tvQuestion.text = repo.getQuestionText(currentAdviseQuestion)
            }
            btnReset.visibility = View.VISIBLE
        }

        btnReset.setOnClickListener() {
            currentAdviseQuestion = 0
            btnJa.visibility = View.VISIBLE
            btnNee.visibility = View.VISIBLE
            tvAdvise.visibility = View.INVISIBLE
            tvQuestion.text = repo.getQuestionText(currentAdviseQuestion)
            btnReset.visibility = View.INVISIBLE
        }
    }
}

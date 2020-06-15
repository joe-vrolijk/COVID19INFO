package com.team1.covid19info.ui.advice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.team1.covid19info.R
import com.team1.covid19info.data.AdviseDataRepository
import kotlinx.android.synthetic.main.fragment_advice.*
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.Observer
import com.team1.covid19info.model.AdviceItem
import com.team1.covid19info.model.AdviceQuestionItem


class AdviceFragment : Fragment() {
    companion object {
        fun newInstance() = AdviceFragment()
    }

    private var itemList: List<AdviceItem> = emptyList()

    private lateinit var viewModel: AdviceViewModel

    private fun initViewModel() {
        viewModel = AdviceViewModel(requireContext())
        viewModel.getQuestionsFromFireBase()
    }


    fun getQuestions(): List<AdviceItem> {
        return itemList
    }

    private val repo = AdviseDataRepository()
    var currentAdviseQuestion: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_advice, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = AdviceViewModel(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()

        btnJa.setOnClickListener { viewModel.clickYes() }
        btnNee.setOnClickListener { viewModel.clickNo() }
        btnReset.setOnClickListener { viewModel.clickReset() }

        btnInfo.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.rivm.nl/coronavirus-covid-19/vragen-antwoorden")
            )
            startActivity(browserIntent)
        }
        viewModel.currentAdviceItem.observe(viewLifecycleOwner, Observer {
            if (it is AdviceQuestionItem) {

                btnJa.visibility = View.VISIBLE
                btnNee.visibility = View.VISIBLE
                tvAdvise.visibility = View.INVISIBLE
                tvQuestion.text = it.questionText
                btnReset.visibility = View.GONE
                btnInfo.visibility = View.GONE
            } else {

            }
        })
        //        btnReset.setOnClickListener() {
//            currentAdviseQuestion = 0
//            btnJa.visibility = View.VISIBLE
//            btnNee.visibility = View.VISIBLE
//            tvAdvise.visibility = View.INVISIBLE
//            tvQuestion.text = getQuestionText(currentAdviseQuestion)
//            btnReset.visibility = View.GONE
//            btnInfo.visibility = View.GONE
//        }

//        tvQuestion.text = getQuestionText(currentAdviseQuestion)
//        tvAdvise.text = getAdviseText(currentAdviseQuestion)
//        btnReset.visibility = View.GONE
//        btnInfo.visibility = View.GONE

//        if (getAdviseText(currentAdviseQuestion) != null) {
//            btnJa.visibility = View.GONE
//            btnNee.visibility = View.GONE
//        }
//
//        btnJa.setOnClickListener() {
//            currentAdviseQuestion = getLinkYesId(currentAdviseQuestion)
//            if (isAdvice(currentAdviseQuestion)) {
//                btnJa.visibility = View.GONE
//                btnNee.visibility = View.GONE
//                tvAdvise.visibility = View.VISIBLE
//                tvQuestion.text = getQuestionText(currentAdviseQuestion)
//                tvAdvise.text = getAdviseText(currentAdviseQuestion)
//            } else {
//                btnJa.visibility = View.VISIBLE
//                btnNee.visibility = View.VISIBLE
//                tvAdvise.visibility = View.INVISIBLE
//                tvQuestion.text = getQuestionText(currentAdviseQuestion)
//            }
//            btnReset.visibility = View.VISIBLE
//            if (isAdvice(currentAdviseQuestion)) {
//                btnInfo.visibility = View.VISIBLE
//            } else {
//                btnInfo.visibility = View.GONE
//            }
//        }
//
//        btnNee.setOnClickListener() {
//            currentAdviseQuestion = getLinkNoId(currentAdviseQuestion)
//            if (isAdvice(currentAdviseQuestion)) {
//                btnJa.visibility = View.GONE
//                btnNee.visibility = View.GONE
//                tvAdvise.visibility = View.VISIBLE
//                tvQuestion.text = getQuestionText(currentAdviseQuestion)
//                tvAdvise.text = getAdviseText(currentAdviseQuestion)
//            } else {
//                btnJa.visibility = View.VISIBLE
//                btnNee.visibility = View.VISIBLE
//                tvAdvise.visibility = View.INVISIBLE
//                tvQuestion.text = getQuestionText(currentAdviseQuestion)
//            }
//            btnReset.visibility = View.VISIBLE
//            if (isAdvice(currentAdviseQuestion)) {
//                btnInfo.visibility = View.VISIBLE
//            } else {
//                btnInfo.visibility = View.GONE
//            }
//        }
//

//


    }

}

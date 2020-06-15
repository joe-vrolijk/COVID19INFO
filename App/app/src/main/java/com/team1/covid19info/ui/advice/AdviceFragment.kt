package com.team1.covid19info.ui.advice

import android.app.PendingIntent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.team1.covid19info.R
import kotlinx.android.synthetic.main.fragment_advice.*
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
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
        viewModel = AdviceViewModel(requireContext())

        btnJa.setOnClickListener { viewModel.clickYes() }
        btnNee.setOnClickListener { viewModel.clickNo() }
        btnReset.setOnClickListener { viewModel.resetAdviseQuestion() }

        btnInfo.setOnClickListener {
            openCustomTab("https://www.rivm.nl/coronavirus-covid-19/vragen-antwoorden")
        }

        btnReset.setOnClickListener {
            viewModel.resetAdviseQuestion()
        }
        viewModel.currentAdviceItem.observe(viewLifecycleOwner, Observer {
            tvQuestion.text = it.questionText
            plLoading.visibility = View.GONE
            tvQuestion.visibility = View.VISIBLE
            btnReset.visibility = if (it.adviceQuestionId == 0.toLong()) View.GONE else View.VISIBLE
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

    private fun openCustomTab(url: String) {
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(ContextCompat.getColor(this.requireContext(), R.color.colorPrimary))
        builder.addDefaultShareMenuItem()

        val anotherCustomTab = CustomTabsIntent.Builder().build()
        val requestCode = 100
        val intent = anotherCustomTab.intent
        intent.setData(Uri.parse(url))

        val pendingIntent = PendingIntent.getActivity(
            this.requireContext(),
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        builder.setShowTitle(true)
        builder.setStartAnimations(
            this.requireContext(),
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
        builder.setExitAnimations(
            this.requireContext(),
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )

        val customTabsIntent = builder.build()

        customTabsIntent.launchUrl(this.requireContext(), Uri.parse(url))
    }
}

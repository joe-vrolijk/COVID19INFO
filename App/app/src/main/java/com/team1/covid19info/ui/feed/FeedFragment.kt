package com.team1.covid19info.ui.feed

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.team1.covid19info.R
import com.team1.covid19info.model.NewsItem
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {

    private val newsItems = arrayListOf<NewsItem>()
    private val newsRvAdapter = FeedRvAdapter(newsItems, {newsItem -> onNewsItemClick(newsItem)})

    companion object {
        fun newInstance() = FeedFragment()
    }

    private lateinit var viewModel: FeedViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        initViewModel()
//        viewModel = FeedViewModel(requireContext())
    }

    private fun initViews(){
        feed_rv.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        feed_rv.adapter = newsRvAdapter
    }

    private fun initViewModel(){
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.newsItems.observe(this, Observer {
            this.newsItems
        })

    }



}

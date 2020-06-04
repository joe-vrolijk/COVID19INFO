package com.team1.covid19info.ui.feed


import android.app.PendingIntent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team1.covid19info.R
import com.team1.covid19info.data.FirebaseDao
import com.team1.covid19info.model.NewsItem
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : Fragment() {

    private val newsItems = arrayListOf<NewsItem>()
    private val feedRvAdapter = FeedRvAdapter(newsItems)
    private val firebaseDao = FirebaseDao()

    companion object {
        fun newInstance() = FeedFragment()
        const val NEWSITEM = "NewsItem"
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
    }

    private fun initViews(){
        feed_rv.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        feed_rv.adapter = feedRvAdapter
        feedRvAdapter.setOnItemClickListener { item -> openCustomTab(item.url)  }
    }

    private fun initViewModel(){
        viewModel = FeedViewModel(requireContext())
        viewModel.newsItems.observe(viewLifecycleOwner, Observer {
            newsItems.clear()
            newsItems.addAll(it)
            feedRvAdapter.notifyDataSetChanged()
        })
        viewModel.getCovidNews()
    }


    private fun openCustomTab(url: String){
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(ContextCompat.getColor(this.requireContext(), R.color.colorPrimary))
        builder.addDefaultShareMenuItem()

        val anotherCustomTab = CustomTabsIntent.Builder().build()
        val requestCode = 100
        val intent = anotherCustomTab.intent
        intent.setData(Uri.parse(url))

        val pendingIntent = PendingIntent.getActivity(this.requireContext(), requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        builder.setShowTitle(true)
        builder.setStartAnimations(this.requireContext(), android.R.anim.fade_in, android.R.anim.fade_out)
        builder.setExitAnimations(this.requireContext(), android.R.anim.fade_in, android.R.anim.fade_out)

        val customTabsIntent = builder.build()

        customTabsIntent.launchUrl(this.requireContext(), Uri.parse(url))
    }


}

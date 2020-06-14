package com.team1.covid19info.ui.feed


import android.app.PendingIntent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.team1.covid19info.R
import com.team1.covid19info.model.NewsItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_feed.*
import java.util.*


class FeedFragment : Fragment() {

    private val newsItems = arrayListOf<NewsItem>()
    private val feedRvAdapter = FeedRvAdapter(newsItems)
    private val timer = Timer()
    private val updatePeriod = 3600000L // in ms

    companion object {
        fun newInstance() = FeedFragment()
        const val NEWSITEM = "NewsItem"
    }

    private lateinit var viewModel: FeedViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        initViewModel()
        updateNews()

        itemsswipetorefresh.setOnRefreshListener {
            viewModel.refreshNewsItems()
            viewModel.getDbNewsItems()
            viewModel.newsItems.observe(viewLifecycleOwner, Observer {
                newsItems.clear()
                newsItems.addAll(it)
                feedRvAdapter.notifyDataSetChanged()
            })
            itemsswipetorefresh.isRefreshing = false
        }
    }

    private fun initViews(){
        feed_rv.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        feed_rv.adapter = feedRvAdapter
        feedRvAdapter.setOnItemClickListener { item -> openCustomTab(item.url!!) }
    }

    private fun initViewModel(){
        viewModel = FeedViewModel(requireContext())
        viewModel.getDbNewsItems()
        viewModel.newsItems.observe(viewLifecycleOwner, Observer {
            newsItems.clear()
            newsItems.addAll(it)
            feedRvAdapter.notifyDataSetChanged()
        })
    }

    private fun updateNews(){
        Log.i("*** UPDATE PERIOD ***", String.format("Time in mins: %d", updatePeriod / 60000))
        val task = object: TimerTask(){
            override fun run() {
                viewModel.refreshNewsItems()
                viewModel.getDbNewsItems()
                Toast.makeText(context, "Updated NewsItems", Toast.LENGTH_SHORT).show()
            }
        }
        // run every 60 min = 3600000 by default. Change updatePeriod for interval changes in ms
        timer.scheduleAtFixedRate(task, updatePeriod, 5000)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.feed_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> viewModel.refreshNewsItems()
            else -> {
            }
        }
        return true
    }

    override fun onPause() {
        super.onPause()
        timer.cancel()
        (activity as AppCompatActivity).tbToolbar.menu.clear()

    }

    override fun onResume() {
        super.onResume()
        updateNews()
        (activity as AppCompatActivity).tbToolbar.inflateMenu(R.menu.feed_menu)
        (activity as AppCompatActivity).tbToolbar.setOnMenuItemClickListener{
            when (it.itemId) {
                R.id.action_refresh -> {
                    viewModel.refreshNewsItems()
                    viewModel.getDbNewsItems()
                }
                else -> {
                }
            }
            true
        }
    }
}

package com.team1.covid19info.ui.feed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.team1.covid19info.R
import com.team1.covid19info.model.NewsItem
import kotlinx.android.synthetic.main.component_newsitem.view.*

class FeedRvAdapter(private val newsItems: List<NewsItem>) : RecyclerView.Adapter<FeedRvAdapter.ViewHolder>() {

    private lateinit var context: Context
    private var listener: ((item: NewsItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedRvAdapter.ViewHolder {
        context = parent.context;

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.component_newsitem, parent, false)
        )
    }

    override fun getItemCount(): Int = newsItems.size;

    override fun onBindViewHolder(holder: FeedRvAdapter.ViewHolder, position: Int) = holder.bind(newsItems[position])

    fun setOnItemClickListener(listener: (item: NewsItem) -> Unit) {
        this.listener = listener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.feedrvreadmore.setOnClickListener{
                listener?.invoke(newsItems[adapterPosition])
            }

        }

        fun bind(newsItem: NewsItem){
            Glide.with(context).load(newsItem.image?.thumbnail?.contentUrl)
                .into(itemView.feedrv_img)
            itemView.feedrv_title.text = newsItem.title
            itemView.feedrv_pubdate.text = newsItem.pubDate.toString()
            itemView.feedrv_content.text = newsItem.content + "....."
        }
    }
}

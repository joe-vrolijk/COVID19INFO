package com.team1.covid19info.ui.feed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.team1.covid19info.model.NewsItem

class FeedRvAdapter(private val newsItems: List<NewsItem>, private val onClick: (NewsItem) -> Unit) : RecyclerView.Adapter<FeedRvAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedRvAdapter.ViewHolder {
        context = parent.context;

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.component_newsitem, parent, false)
        )
    }

    override fun getItemCount(): Int = newsItems.size;

    override fun onBindViewHolder(holder: FeedRvAdapter.ViewHolder, position: Int) = holder.bind(newsItems[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener{
                onClick(newsItems[adapterPosition])
            }
        }

        fun bind(newsItem: NewsItem){
//            itemView.tvNumber.text = (movies.indexOf(movie) + 1).toString()
//            Glide.with(context).load(movie.getPosterImageUrl()).into(itemView.ivPoster)
        }
    }
}
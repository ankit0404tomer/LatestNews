package com.example.latestnews.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.latestnews.R
import com.example.latestnews.network.rx.viewstates.NewsDataVS
import com.example.latestnews.utils.AppUtils.ANONYMOUS
import com.example.latestnews.utils.AppUtils.NO_DESCRIPTION
import kotlinx.android.synthetic.main.news_item_cell.view.*

class NewsListAdapter(var newsList: List<NewsDataVS>) : RecyclerView.Adapter<NewsListAdapter.MyViewHolder>() {

    private var selectedNewsitem = MutableLiveData<NewsDataVS>()
    val getSelectedNewsitem: LiveData<NewsDataVS>
        get() = selectedNewsitem

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item_cell, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val newsFeed = newsList[position]
        Glide.with(holder.itemView.news_image.context)
            .load(newsFeed.urlToImage)
            .placeholder(R.drawable.ic_news_icon)
            .error(R.drawable.ic_news_icon)
            .into(holder.image)
        holder.title.text = newsFeed?.title ?: NO_DESCRIPTION

        holder.author.text = newsFeed?.author ?: ANONYMOUS

        holder.itemView.setOnClickListener {
            selectedNewsitem.value = newsFeed
        }

    }

    override fun getItemCount(): Int {
        return newsList.size
    }


    class MyViewHolder(binding: View) : RecyclerView.ViewHolder(binding.rootView) {
        var image: ImageView = binding.findViewById(R.id.news_image)
        var title: TextView = binding.findViewById(R.id.news_title)
        var author: TextView = binding.findViewById(R.id.news_author)
    }

}

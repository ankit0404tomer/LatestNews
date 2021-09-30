package com.example.latestnews.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.latestnews.R
import com.example.latestnews.network.rx.viewstates.NewsDataVS
import com.example.latestnews.utils.AppUtils
import com.example.latestnews.utils.AppUtils.ANONYMOUS
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.detail_news_fragment.*
import javax.inject.Inject

class DetailNewsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(DetailNewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_news_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val newsData = arguments?.getParcelable<NewsDataVS>(AppUtils.NEWS_DATA)
            initViews(newsData)

            viewModel.fetchNewsLikesComment(newsData?.url)
            viewModel.getLikesnComments.observe(viewLifecycleOwner, {
                news_likes.text = "Likes : " + it.first
                news_comments.text = "Comments : " + it.second
            })
        }

    }

    private fun initViews(newsData: NewsDataVS?) {
        val author = newsData?.author ?: ANONYMOUS
        news_author.text = "By :$author"
        news_title.text = newsData?.title
        Glide.with(this).load(newsData?.urlToImage)
            .placeholder(R.drawable.ic_news_icon)
            .error(R.drawable.ic_news_icon)
            .into(news_image)
    }
}

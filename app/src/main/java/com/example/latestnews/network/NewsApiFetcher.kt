package com.example.latestnews.network

import com.example.latestnews.model.Comments
import com.example.latestnews.model.Likes
import com.example.latestnews.model.NewsList
import io.reactivex.Single

class NewsApiFetcher constructor(private val apiBackend: NewsBackend) : NewsFetcher {

    override fun fetchNewsList(newsUrl: String): Single<NewsList> {
        return apiBackend.fetchNewsList(newsUrl)
    }

    override fun fetchNewsLikes(newsLikeUrl: String): Single<Likes> {
        return apiBackend.fetchNewsLikes(newsLikeUrl)
    }

    override fun fetchNewsComments(newsCommentUrl: String): Single<Comments> {
        return apiBackend.fetchNewsComments(newsCommentUrl)
    }
}

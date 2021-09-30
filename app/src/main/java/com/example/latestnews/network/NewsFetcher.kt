package com.example.latestnews.network

import com.example.latestnews.model.Comments
import com.example.latestnews.model.Likes
import com.example.latestnews.model.NewsList
import io.reactivex.Single

interface NewsFetcher {

    fun fetchNewsList(newsUrl: String): Single<NewsList>

    fun fetchNewsLikes(newsLikeUrl: String): Single<Likes>

    fun fetchNewsComments(newsCommentUrl: String): Single<Comments>
}

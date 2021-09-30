package com.example.latestnews.network

import com.example.latestnews.model.Comments
import com.example.latestnews.model.Likes
import com.example.latestnews.model.NewsList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface NewsBackend {

    @GET
    fun fetchNewsList(@Url newsUrl: String): Single<NewsList>

    @GET
    fun fetchNewsLikes(@Url newsLikeUrl: String): Single<Likes>

    @GET
    fun fetchNewsComments(@Url newsCommentUrl: String): Single<Comments>
}

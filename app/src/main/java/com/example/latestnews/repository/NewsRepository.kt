package com.example.latestnews.repository

import com.example.latestnews.model.Comments
import com.example.latestnews.model.Likes
import com.example.latestnews.network.NewsApiFetcher
import com.example.latestnews.network.rx.ResponseState
import com.example.latestnews.network.rx.SchedulingStrategyFactory
import com.example.latestnews.network.viewstates.NewsListViewState
import com.example.latestnews.network.viewstates.NewsListViewStateConverter
import io.reactivex.Observable

class NewsRepository constructor(
    private val apiFetcher: NewsApiFetcher,
    private val newsListViewStateConverter: NewsListViewStateConverter,
    private val schedulingStrategyFactory: SchedulingStrategyFactory
) {


    fun fetchLatestNewsList(url: String): Observable<ResponseState<NewsListViewState>> {
        return apiFetcher.fetchNewsList(url)
            .map(newsListViewStateConverter)
            .toObservable()
            .startWith(ResponseState.Loading)
            .compose(schedulingStrategyFactory.create())
    }


    fun fetchNewsLikes(newsLikeUrl: String): Observable<Likes> {
        return apiFetcher.fetchNewsLikes(newsLikeUrl)
            .toObservable()
            .compose(schedulingStrategyFactory.create())
    }


    fun fetchNewsComments(newsCommentUrl: String): Observable<Comments> {
        return apiFetcher.fetchNewsComments(newsCommentUrl)
            .toObservable()
            .compose(schedulingStrategyFactory.create())
    }

}

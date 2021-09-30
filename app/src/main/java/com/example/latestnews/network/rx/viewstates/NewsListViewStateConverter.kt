package com.example.latestnews.network.rx.viewstates

import com.example.latestnews.model.NewsList
import com.example.latestnews.network.rx.ResponseState
import io.reactivex.functions.Function

class NewsListViewStateConverter : Function<NewsList, ResponseState<NewsListViewState>> {
    override fun apply(newsList: NewsList): ResponseState<NewsListViewState> {
        val newsListData = mutableListOf<NewsDataVS>()

        newsList.articles.forEachIndexed { index, articles ->
            newsListData.add(
                NewsDataVS(
                    author = articles.author,
                    description = articles.description,
                    title = articles.title,
                    url = articles.url,
                    urlToImage = articles.urlToImage,
                    id = articles.source.id
                )
            )
        }

        return ResponseState.Success(NewsListViewState(newsListData))
    }

}

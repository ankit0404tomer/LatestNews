package com.example.latestnews

import com.example.latestnews.model.NewsList
import com.example.latestnews.network.NewsApiFetcher
import com.example.latestnews.network.rx.ResponseState
import com.example.latestnews.network.viewstates.NewsListViewStateConverter
import com.example.latestnews.repository.NewsRepository
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryTest {


    @Mock
    private lateinit var newsList: NewsList

    @Mock
    private lateinit var apiFetcher: NewsApiFetcher

    @Mock
    private lateinit var newsListViewStateConverter: NewsListViewStateConverter

    @Mock
    private lateinit var responseState: ResponseState<Nothing>

    private lateinit var repository: NewsRepository
    var newsUrl = "https://newsapi.org/v2/top-headlines?country=us&apiKey=bb721cecb3ad4072b338cea763443046"

    @Before
    fun setUp() {
        repository = NewsRepository(apiFetcher, newsListViewStateConverter, TestSchedulingStrategyFactory.immediate())
    }

    @Test
    fun `should return correct newsViewState when fetchLatestNewsList is called`() {
        whenever(apiFetcher.fetchNewsList(newsUrl)).thenReturn(Single.just(newsList))

        whenever(newsListViewStateConverter.apply(newsList)).thenReturn(responseState)
        val observer = repository.fetchLatestNewsList(newsUrl).test()
        observer.assertValues(ResponseState.Loading, responseState)
    }


}

package com.example.latestnews.ui.newslist

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latestnews.network.rx.ResponseState
import com.example.latestnews.network.viewstates.NewsDataVS
import com.example.latestnews.network.viewstates.NewsListViewState
import com.example.latestnews.repository.NewsRepository
import com.example.latestnews.utils.AppUtils
import com.example.latestnews.utils.AppUtils.NEWS_LIST_URL
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsListViewModel @Inject constructor(
    var newsRepository: NewsRepository
) : ViewModel() {


    init {
        fetchNewsList()
    }
    private lateinit var subscription: Disposable

    private var newsList = MutableLiveData<ResponseState<NewsListViewState>>()
    val getNewsList: LiveData<ResponseState<NewsListViewState>>
        get() = newsList

    fun fetchNewsList() {
        subscription = newsRepository.fetchLatestNewsList(NEWS_LIST_URL)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                subscription = it
            }
            .subscribe(
                { result ->
                    newsList.value = result
                },
                { error ->
                    error.printStackTrace()
                }
            )

    }


    fun createArguments(newsDataVS: NewsDataVS): Bundle {
        val bundle = Bundle()
        bundle.putParcelable(AppUtils.NEWS_DATA, newsDataVS)
        return bundle
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}

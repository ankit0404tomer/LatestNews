package com.example.latestnews.ui.details

import android.util.Log
import android.util.Pair
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latestnews.model.Comments
import com.example.latestnews.model.Likes
import com.example.latestnews.repository.NewsRepository
import com.example.latestnews.utils.AppUtils
import com.example.latestnews.utils.AppUtils.convertUrltoArticleID
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailNewsViewModel @Inject constructor(
    var newsRepository: NewsRepository
) : ViewModel() {

    private lateinit var subscription: Disposable

    private var LikesnComments = MutableLiveData<Pair<Int, Int>>()
    val getLikesnComments: LiveData<Pair<Int, Int>>
        get() = LikesnComments


    fun fetchNewsLikesComment(url: String?) {

        subscription = Observable.zip(
            newsRepository.fetchNewsLikes(AppUtils.BASE_NEWS_LIKE_URL + convertUrltoArticleID(url))
                .subscribeOn(Schedulers.io()),
            newsRepository.fetchNewsComments(AppUtils.BASE_NEWS_COMMENTS_URL + convertUrltoArticleID(url))
                .subscribeOn(Schedulers.io()),
            BiFunction { likes: Likes,
                         comments: Comments ->
                combineResult(likes, comments)
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    LikesnComments.value = result
                    Log.d("Ankit", "Api response :$result")
                },
                { error ->
                    error.printStackTrace()
                }
            )

    }

    private fun combineResult(likes: Likes, comments: Comments): Pair<Int, Int> {

        val result = Pair(
            likes.likes,
            comments.comments
        )
        return result
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


}

package com.example.latestnews.network.viewstates

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class NewsListViewState(
    val newsList: List<NewsDataVS>
) {
    companion object {
        private const val EMPTY_STRING = ""

        val EMPTY = NewsDataVS(
            author = EMPTY_STRING,
            description = EMPTY_STRING,
            title = EMPTY_STRING,
            url = EMPTY_STRING,
            urlToImage = EMPTY_STRING,
            id = EMPTY_STRING
        )
    }
}

@Parcelize
data class NewsDataVS(
    val author: String?,
    val description: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?,
    val id: String?
) : Parcelable

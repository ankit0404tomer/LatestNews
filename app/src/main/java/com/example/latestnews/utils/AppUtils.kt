package com.example.latestnews.utils


object AppUtils {

    const val BASE_URL = "https://newsapi.org/v2/"
    const val NEWS_DATA = "news_data"
    const val BASE_NEWS_LIKE_URL = "https://cn-news-info-api.herokuapp.com/likes/"
    const val BASE_NEWS_COMMENTS_URL = "https://cn-news-info-api.herokuapp.com/comments/"
    const val NEWS_LIST_URL = "top-headlines?country=us&apiKey=bb721cecb3ad4072b338cea763443046"
    const val ANONYMOUS = "Anonymous"
    const val NO_DESCRIPTION= "No Description"

    fun convertUrltoArticleID(str: String?): String? {
        return str?.replace("https://", "")?.replace("/", "-")
    }

}

package com.example.latestnews.di.module

import com.example.latestnews.network.NewsApiFetcher
import com.example.latestnews.network.NewsBackend
import com.example.latestnews.network.rx.AndroidSchedulingStrategyFactory
import com.example.latestnews.network.rx.viewstates.NewsListViewStateConverter
import com.example.latestnews.repository.NewsRepository
import com.example.latestnews.utils.AppUtils.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Module which provides all required dependencies about network
 */
@Module
class NewsListModule {


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().newBuilder()
    }


    @Provides
    fun provideNewsFetcherNewsFetcherService(
        retrofitBuilder: Retrofit.Builder
    ): NewsBackend {

        return retrofitBuilder
            .build()
            .create(NewsBackend::class.java)
    }


    @Provides
    fun providesNewsRepository(
        apiFetcher: NewsApiFetcher,
        converter: NewsListViewStateConverter
    ): NewsRepository {

        return NewsRepository(
            apiFetcher,
            converter,
            AndroidSchedulingStrategyFactory.io()
        )
    }

    @Provides
    fun providesNewsApiFetcher(apiBackend: NewsBackend): NewsApiFetcher {
        return NewsApiFetcher(apiBackend)
    }


    @Provides
    fun providesNewsListViewStateConverter(): NewsListViewStateConverter {
        return NewsListViewStateConverter()
    }


}


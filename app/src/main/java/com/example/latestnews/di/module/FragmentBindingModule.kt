package com.example.latestnews.di.module

import com.example.latestnews.di.scope.FragmentScoped
import com.example.latestnews.ui.details.DetailNewsFragment
import com.example.latestnews.ui.newslist.NewsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun provideNewsListFragment(): NewsListFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun provideDetailNewsFragment(): DetailNewsFragment
}

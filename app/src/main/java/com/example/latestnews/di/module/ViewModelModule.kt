package com.example.latestnews.di.module

import androidx.lifecycle.ViewModel
import com.example.latestnews.di.scope.ViewModelScoped
import com.example.latestnews.ui.details.DetailNewsViewModel
import com.example.latestnews.ui.newslist.NewsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


/**
 *<p>
 * The ViewModelModule is used to provide a mapData of view models through dagger that is used by the ViewModelFactoryModule class.
 *</p>
 */

@Module
abstract class ViewModelModule : ViewModelFactoryModule() {

    @Binds
    @IntoMap
    @ViewModelScoped(NewsListViewModel::class)
    internal abstract fun newsListViewModel(viewModel: NewsListViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelScoped(DetailNewsViewModel::class)
    internal abstract fun newsDetailNewsViewModel(viewModel: DetailNewsViewModel): ViewModel
}

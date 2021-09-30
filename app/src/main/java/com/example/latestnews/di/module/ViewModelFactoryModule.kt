package com.example.latestnews.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 *<h1></h1>

 *<p>
 * ViewModelFactoryModule class basically helps you dynamically create ViewModels for your Activities and Fragments.
 * The ViewModelFactoryclass has a list of providers and can create any ViewModel that was bound.
 * Fragments and Activities can nowjust inject the factory and retrieve their ViewModel.
 *
 *</p>

 */
@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: BaseAppViewModelFactory): ViewModelProvider.Factory
}

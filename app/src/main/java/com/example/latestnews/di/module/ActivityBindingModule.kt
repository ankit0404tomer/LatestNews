package com.example.latestnews.di.module

import com.example.latestnews.MainActivity
import com.example.latestnews.di.scope.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(
    includes = [ViewModelModule::class]
)

abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [FragmentBindingModule::class])
    abstract fun bindMainActivity(): MainActivity
}

package com.example.latestnews.network.rx

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer

class SchedulingStrategy<T> constructor(
    private val subscribingScheduler: Scheduler,
    private val observingScheduler: Scheduler
) :
    ObservableTransformer<T, T>, SingleTransformer<T, T> {

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream
            .subscribeOn(subscribingScheduler)
            .observeOn(observingScheduler)
    }

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream
            .subscribeOn(subscribingScheduler)
            .observeOn(observingScheduler)
    }
}

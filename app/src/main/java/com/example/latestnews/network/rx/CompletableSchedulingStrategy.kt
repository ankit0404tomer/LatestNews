package com.example.latestnews.network.rx

import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.CompletableTransformer
import io.reactivex.Maybe
import io.reactivex.MaybeSource
import io.reactivex.MaybeTransformer
import io.reactivex.Scheduler

class CompletableSchedulingStrategy constructor(
    private val subscribingScheduler: Scheduler,
    private val observingScheduler: Scheduler
) : CompletableTransformer {
    override fun apply(upstream: Completable): CompletableSource {
        return upstream
            .subscribeOn(subscribingScheduler)
            .observeOn(observingScheduler)
    }
}


class MayBeSchedulingStrategy<T> constructor(
    private val subscribingScheduler: Scheduler,
    private val observingScheduler: Scheduler
) :
    MaybeTransformer<T, T> {
    override fun apply(upstream: Maybe<T>): MaybeSource<T> {
        return upstream
            .subscribeOn(subscribingScheduler)
            .observeOn(observingScheduler)
    }

}

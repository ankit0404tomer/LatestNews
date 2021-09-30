package com.example.latestnews.network.rx

import io.reactivex.Scheduler

open class SchedulingStrategyFactory constructor(
    private val subscribingScheduler: Scheduler,
    private val observingScheduler: Scheduler
) {
    fun <T> create(): SchedulingStrategy<T> {
        return SchedulingStrategy(subscribingScheduler, observingScheduler)
    }

    fun createCompletableScheduler(): CompletableSchedulingStrategy {
        return CompletableSchedulingStrategy(subscribingScheduler, observingScheduler)
    }


    fun <T> createMaybeScheduler(): MayBeSchedulingStrategy<T> {
        return MayBeSchedulingStrategy(subscribingScheduler, observingScheduler)
    }

    fun getSubscribingScheduler(): Scheduler {
        return subscribingScheduler
    }

}

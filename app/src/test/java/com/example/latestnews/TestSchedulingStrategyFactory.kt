package com.example.latestnews

import com.example.latestnews.network.rx.SchedulingStrategyFactory
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers


open class TestSchedulingStrategyFactory(
    subscribingScheduler: Scheduler,
    observingScheduler: Scheduler
) :
    SchedulingStrategyFactory(subscribingScheduler, observingScheduler) {

    companion object {
        fun immediate(): TestSchedulingStrategyFactory {
            return TestSchedulingStrategyFactory(Schedulers.trampoline(), Schedulers.trampoline())
        }
    }

    fun subscribing(scheduler: Scheduler): TestSchedulingStrategyFactory {
        return TestSchedulingStrategyFactory(scheduler, Schedulers.trampoline())
    }
}



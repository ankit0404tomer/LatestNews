package com.example.latestnews.network.rx

import io.reactivex.functions.Function

interface Converter<T, R> : Function<T, R>

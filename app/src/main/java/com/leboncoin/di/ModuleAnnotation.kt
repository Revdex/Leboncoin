package com.leboncoin.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CoroutineIODispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CoroutineMainDispatcher
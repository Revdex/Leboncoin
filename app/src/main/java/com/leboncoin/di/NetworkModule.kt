package com.leboncoin.di

import android.content.Context
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.leboncoin.Constants
import com.leboncoin.data.remote.service.ServiceEndpoint
import com.leboncoin.utils.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpCache(@ApplicationContext context: Context): Cache {
        return Cache(context.cacheDir, Constants.Network.DISK_CACHE_SIZE.toLong())
    }

    @Provides
    @Singleton
    fun provideLogInterceptor(): Interceptor {
        return HttpLoggingInterceptor { message -> Logger.v(message) }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideObjectMapper() = ObjectMapper().apply {
        setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
        setSerializationInclusion(JsonInclude.Include.NON_NULL)
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache, logInterceptor: Interceptor) = OkHttpClient.Builder().apply {
        addInterceptor(logInterceptor)
        cache(cache)
        connectTimeout(Constants.Network.CONNECT_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(Constants.Network.READ_TIMEOUT, TimeUnit.SECONDS)
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, mapper: ObjectMapper) = Retrofit.Builder()
        .baseUrl(Constants.Network.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(JacksonConverterFactory.create(mapper))
        .callbackExecutor(Executors.newSingleThreadExecutor()).build()

    @Provides
    @Singleton
    fun provideServiceEndpoint(retrofit: Retrofit): ServiceEndpoint {
        return retrofit.create(ServiceEndpoint::class.java)
    }

}
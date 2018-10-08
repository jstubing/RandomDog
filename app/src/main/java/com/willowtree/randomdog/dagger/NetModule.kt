package com.willowtree.randomdog.dagger

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.willowtree.randomdog.data.source.remote.DogApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideConverterFactory() = MoshiConverterFactory.create()

    @Provides
    @Singleton
    fun provideCallAdapterFactory() = CoroutineCallAdapterFactory()

    @Provides
    @Singleton
    fun provideRetrofit(converterFactory: MoshiConverterFactory,
                        callAdapterFactory: CoroutineCallAdapterFactory) = Retrofit.Builder()
            .baseUrl(DogApi.BASE_URL)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()

    @Provides
    @Singleton
    fun provideDogApi(retrofit: Retrofit) = retrofit.create(DogApi::class.java)
}
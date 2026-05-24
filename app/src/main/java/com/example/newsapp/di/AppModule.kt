package com.example.newsapp.di

import com.example.newsapp.core.common.DefaultDispatcherProvider
import com.example.newsapp.core.common.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider {

        return DefaultDispatcherProvider()
    }
}
package com.example.coroutines.di

import com.example.coroutines.service.RetrofitBuilder
import com.example.coroutines.service.ServiceGitHub
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    fun provideGitHubService(): ServiceGitHub = RetrofitBuilder.getGitHubService()
}
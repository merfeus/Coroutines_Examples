package com.example.coroutines.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getGitHubService(): ServiceGitHub{
        return retrofit.create(ServiceGitHub::class.java)
    }
}
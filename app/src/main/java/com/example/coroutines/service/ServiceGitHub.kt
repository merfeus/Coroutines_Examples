package com.example.coroutines.service

import com.example.coroutines.model.GitHub
import com.example.coroutines.model.GitUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceGitHub {

    @GET("/search/repositories")
    suspend fun fecthRepo(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
    ): Response<GitHub>

    @GET("/users")
    suspend fun fechtUser(): Response<List<GitUser>>
}
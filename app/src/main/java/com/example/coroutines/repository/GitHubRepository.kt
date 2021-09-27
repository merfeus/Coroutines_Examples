package com.example.coroutines.repository

import com.example.coroutines.model.GitHub
import com.example.coroutines.model.GitUser
import com.example.coroutines.service.ServiceGitHub
import kotlinx.coroutines.*
import retrofit2.Response
import javax.inject.Inject

class GitHubRepository @Inject constructor(private val service: ServiceGitHub) {

    suspend fun getGitRepository(): Deferred<GitHub?> {
        return CoroutineScope(Dispatchers.Default).async {
            val response = service.fecthRepo(language = "Kotlin", sort = "stars", page = 1)
            processData(response)
        }
    }

    private fun <T> processData(response: Response<T>): T? {
        return if (response.isSuccessful) response.body() else null
    }


    suspend fun getUsers(): List<GitUser>? {
        return withContext(CoroutineScope(Dispatchers.Default).coroutineContext) {
            val response =
                service.fechtUser()
            processData(response)
        }
    }


}
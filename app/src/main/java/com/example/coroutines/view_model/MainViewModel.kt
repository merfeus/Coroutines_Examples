package com.example.coroutines.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutines.model.Repository
import com.example.coroutines.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repositoryGitHub: GitHubRepository
) : ViewModel() {

    private val _GITREPO = MutableLiveData<List<Repository>>()
    val gitRepo: LiveData<List<Repository>> = _GITREPO

    fun getRepositories(language: String, page: Int = 1) {
        viewModelScope.launch {
            repositoryGitHub.getGitRepository().await().let {
                _GITREPO.value = it?.items
            }
        }
    }

    fun getUsers() {
        viewModelScope.launch {
            val users = repositoryGitHub.getUsers()
            println(users)
        }
    }
}
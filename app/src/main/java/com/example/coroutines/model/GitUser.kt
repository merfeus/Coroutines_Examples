package com.example.coroutines.model

import com.google.gson.annotations.SerializedName

data class GitUser(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrlUser: String
)


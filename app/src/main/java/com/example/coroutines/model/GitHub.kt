package com.example.coroutines.model

import com.google.gson.annotations.SerializedName

data class GitHub(
    @SerializedName("total_count")
    val total: Long,
    @SerializedName("items")
    val items: List<Repository>

)

data class Repository(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val nameRepository: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("pulls_url")
    val pullsUrl: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("forks")
    val forks: Int,
    @SerializedName("stargazers_count")
    val stars: Int,
    @SerializedName("owner")
    val owner: Owner,
)

data class Owner(

    @SerializedName("owner_id")
    val id: Long,
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrlOwner: String,
)


package com.oleg.ivanov.test3205team.domain.data

import com.google.gson.annotations.SerializedName

data class GitHubRepoModel(
    @SerializedName("name")
    val nameRepository: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val description: String?,
    @SerializedName("owner")
    val owner: OwnerModel,
)

data class OwnerModel(
    @SerializedName("login")
    val userName: String
)
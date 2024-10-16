package com.oleg.ivanov.data.network

import com.google.gson.annotations.SerializedName
import com.oleg.ivanov.domain.domain.data.OwnerModel

data class GitHubRepoModel(
    @SerializedName("name")
    val nameRepository: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val description: String?,
    @SerializedName("owner")
    val owner: OwnerModel,
)

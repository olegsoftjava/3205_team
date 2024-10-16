package com.oleg.ivanov.domain.domain.data


data class GitHubRepoModelDomain(
    val nameRepository: String,
    val htmlUrl: String,
    val description: String?,
    val owner: OwnerModel?,
)

data class OwnerModel(
    val userName: String?
)

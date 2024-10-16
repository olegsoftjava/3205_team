package com.oleg.ivanov.data.network

import com.oleg.ivanov.domain.domain.data.GitHubRepoModel
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApiService {
    @GET("users/{user}/repos")
    suspend fun getUserRepositories(
        @Path("user") username: String
    ): List<com.oleg.ivanov.domain.domain.data.GitHubRepoModel>
}
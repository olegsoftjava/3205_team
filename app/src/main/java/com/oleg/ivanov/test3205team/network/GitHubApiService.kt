package com.oleg.ivanov.test3205team.network

import com.oleg.ivanov.test3205team.repository.model.GitHubRepoModel
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApiService {
    @GET("users/{user}/repos")
    suspend fun getUserRepositories(
        @Path("user") username: String
    ): List<GitHubRepoModel>
}
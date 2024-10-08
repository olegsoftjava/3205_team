package com.oleg.ivanov.test3205team.presentation.search_screen.view_model

import com.oleg.ivanov.test3205team.repository.model.ErrorModel
import com.oleg.ivanov.test3205team.repository.model.GitHubRepoModel

sealed class SearchViewState {
    internal data class DownloadData(val link: String?) : SearchViewState()
    internal data class SearchData(val list: List<GitHubRepoModel>?, val loadedLinks: List<String>?) : SearchViewState()
    internal data class SearchError(val errorModel: ErrorModel) : SearchViewState()
    internal data object SearchStartLoad : SearchViewState()
    internal data object SearchEndLoad : SearchViewState()
}
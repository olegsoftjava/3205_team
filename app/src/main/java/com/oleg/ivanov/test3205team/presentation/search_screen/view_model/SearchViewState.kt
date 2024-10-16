package com.oleg.ivanov.test3205team.presentation.search_screen.view_model

sealed class SearchViewState {
    internal data class DownloadData(val link: String?) : SearchViewState()
    internal data class SearchData(
        val list: List<com.oleg.ivanov.domain.domain.data.GitHubRepoModel>?,
        val loadedLinks: List<String>?
    ) : SearchViewState()

    internal data class SearchError(val errorModel: com.oleg.ivanov.data.repository.model.ErrorModel) :
        SearchViewState()

    internal data object SearchStartLoad : SearchViewState()
    internal data object SearchEndLoad : SearchViewState()
}
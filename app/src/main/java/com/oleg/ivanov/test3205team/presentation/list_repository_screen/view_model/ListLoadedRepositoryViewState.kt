package com.oleg.ivanov.test3205team.presentation.list_repository_screen.view_model

sealed class ListLoadedRepositoryViewState {
    internal data class Data(val list: List<com.oleg.ivanov.domain.domain.data.DownloadLinkAndFileDomain>?) : ListLoadedRepositoryViewState()
}
package com.oleg.ivanov.test3205team.presentation.list_repository_screen.view_model

import com.oleg.ivanov.test3205team.repository.database.DownloadLinkAndFile

sealed class ListLoadedRepositoryViewState {
    internal data class Data(val list: List<DownloadLinkAndFile>?) : ListLoadedRepositoryViewState()
}
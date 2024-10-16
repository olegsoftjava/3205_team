package com.oleg.ivanov.test3205team.presentation.list_repository_screen.view_model

import com.oleg.ivanov.test3205team.domain.data.DownloadLinkAndFile

sealed class ListLoadedRepositoryViewState {
    internal data class Data(val list: List<DownloadLinkAndFile>?) : ListLoadedRepositoryViewState()
}
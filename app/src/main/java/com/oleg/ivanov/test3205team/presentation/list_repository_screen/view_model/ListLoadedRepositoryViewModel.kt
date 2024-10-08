package com.oleg.ivanov.test3205team.presentation.list_repository_screen.view_model

import kotlinx.coroutines.flow.Flow

interface ListLoadedRepositoryViewModel {
    val viewState: Flow<ListLoadedRepositoryViewState>

    fun load()
}
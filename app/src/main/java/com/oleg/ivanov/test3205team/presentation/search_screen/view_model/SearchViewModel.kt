package com.oleg.ivanov.test3205team.presentation.search_screen.view_model

import kotlinx.coroutines.flow.Flow

interface SearchViewModel {
    val viewState: Flow<SearchViewState>

    fun search(userName: String)
}
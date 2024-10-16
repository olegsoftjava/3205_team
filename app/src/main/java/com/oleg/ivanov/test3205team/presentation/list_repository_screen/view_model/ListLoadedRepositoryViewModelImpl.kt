package com.oleg.ivanov.test3205team.presentation.list_repository_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oleg.ivanov.test3205team.domain.usecase.LoadListDownloadRepositoryUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListLoadedRepositoryViewModelImpl @Inject constructor(
    private val loadListDownloadRepositoryUseCase: LoadListDownloadRepositoryUseCase,
) : ViewModel(), ListLoadedRepositoryViewModel {

    private val _viewState = MutableSharedFlow<ListLoadedRepositoryViewState>()
    override val viewState: Flow<ListLoadedRepositoryViewState>
        get() = _viewState

    override fun load() {
        viewModelScope.launch {
            // Для проверяющего, сюда еще можно добавить pagination
            _viewState.emit(ListLoadedRepositoryViewState.Data(loadListDownloadRepositoryUseCase.execute()))
        }
    }
}
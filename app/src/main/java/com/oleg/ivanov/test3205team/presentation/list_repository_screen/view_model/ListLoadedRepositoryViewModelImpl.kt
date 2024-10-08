package com.oleg.ivanov.test3205team.presentation.list_repository_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oleg.ivanov.test3205team.repository.database.DatabaseProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListLoadedRepositoryViewModelImpl @Inject constructor(
    private val databaseProvider: DatabaseProvider,
) : ViewModel(), ListLoadedRepositoryViewModel {

    private val _viewState = MutableSharedFlow<ListLoadedRepositoryViewState>()
    override val viewState: Flow<ListLoadedRepositoryViewState>
        get() = _viewState

    override fun load() {
        viewModelScope.launch {
            // Для проверяющего, сюда еще можно добавить pagination
            val list = withContext(Dispatchers.IO) {
                databaseProvider.downloadLinkAndFileAppDao().getAll()
            }
            _viewState.emit(ListLoadedRepositoryViewState.Data(list))
        }
    }
}
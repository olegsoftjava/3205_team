package com.oleg.ivanov.test3205team.presentation.search_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oleg.ivanov.test3205team.domain.data.RepositoryNetworkAndLoadedResultModel
import com.oleg.ivanov.test3205team.domain.usecase.LoadListRepositoryUseCase
import com.oleg.ivanov.test3205team.repository.model.ErrorModel
import com.oleg.ivanov.test3205team.repository.provider.ProviderDownloader
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModelImpl @Inject constructor(
    private val loadListRepositoryUseCase: LoadListRepositoryUseCase,
    private val providerDownloader: ProviderDownloader
) : ViewModel(), SearchViewModel {
    private val _viewState = MutableSharedFlow<SearchViewState>()
    override val viewState: Flow<SearchViewState>
        get() = _viewState

    init {
        viewModelScope.launch {
            providerDownloader.urlState.collect {
                _viewState.emit(SearchViewState.DownloadData(it))
            }
        }
    }

    override fun search(userName: String) {
        viewModelScope.launch {
            _viewState.emit(SearchViewState.SearchStartLoad)
            when (val resultSearch = loadListRepositoryUseCase.execute(userName)) {
                is RepositoryNetworkAndLoadedResultModel.DataOK -> {
                    _viewState.emit(
                        SearchViewState.SearchData(
                            list = resultSearch.listGitHub,
                            loadedLinks = resultSearch.listLoaded
                        )
                    )
                }

                is RepositoryNetworkAndLoadedResultModel.DataError -> {
                    _viewState.emit(
                        SearchViewState.SearchError(
                            ErrorModel(
                                resultSearch.e.message ?: "unknown error", 0
                            )
                        )
                    )
                }
            }
            _viewState.emit(SearchViewState.SearchEndLoad)
        }
    }
}
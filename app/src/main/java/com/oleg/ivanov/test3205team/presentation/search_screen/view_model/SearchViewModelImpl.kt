package com.oleg.ivanov.test3205team.presentation.search_screen.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oleg.ivanov.test3205team.app.AppSettings
import com.oleg.ivanov.test3205team.domain.repository.RepositoryData
import com.oleg.ivanov.test3205team.domain.usecase.RepositoryUseCase
import com.oleg.ivanov.test3205team.repository.database.DatabaseProvider
import com.oleg.ivanov.test3205team.repository.model.ErrorModel
import com.oleg.ivanov.test3205team.repository.provider.ProviderDownloader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchViewModelImpl @Inject constructor(
    private val repositoryUseCase: RepositoryUseCase,
    private val databaseProvider: DatabaseProvider,
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
            val resultSearch = repositoryUseCase.execute(userName)
            Log.i("SearchViewModelImpl", "resultSearch = $resultSearch")
            if (resultSearch.isNullOrEmpty()) {
                /**
                 * Для проверяющего - здесь можно было выводить код ошибки, подключить интерсептер в okhttp2 и прокинуть error
                 */
                _viewState.emit(SearchViewState.SearchError(ErrorModel("Не найден", 0)))
            } else {

                val loadedLinks = withContext(Dispatchers.IO) {
                    databaseProvider.downloadLinkAndFileAppDao().getFileByDownloadUserName(userName)?.map {
                        it.link?.replace(AppSettings.PART_URL_FOR_DOWNLOAD, "")?:""
                    }
                }

                _viewState.emit(
                    SearchViewState.SearchData(
                        list = resultSearch,
                        loadedLinks = loadedLinks
                    )
                )
            }
            _viewState.emit(SearchViewState.SearchEndLoad)
        }
    }
}
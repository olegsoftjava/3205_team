package com.oleg.ivanov.test3205team.repository.provider

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class ProviderDownloader {
    private val _urlState = MutableSharedFlow<String>()
    val urlState: Flow<String>
        get() = _urlState

    fun sendUrlLoadedCompleted(url: String) {
        CoroutineScope(Dispatchers.IO).launch {
            _urlState.emit(url)
        }
    }
}
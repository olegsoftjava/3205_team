package com.oleg.ivanov.domain.domain.data

data class DownloadLinkAndFileDomain(
    var id: Long = 0,
    var link: String? = null,
    var file: String? = null,
    var error: Boolean = false,
    var downloadId: Long = 0L,
    var userName: String,
    var repositoryName: String,
    var comleted: Boolean,
)
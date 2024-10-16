package com.oleg.ivanov.test3205team.domain.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class DownloadLinkAndFile(
    @PrimaryKey var id: Long = 0,
    var link: String? = null,
    var file: String? = null,
    var error: Boolean = false,
    var downloadId: Long = 0L,
    var userName: String,
    var repositoryName: String,
    var comleted: Boolean,
)
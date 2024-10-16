package com.oleg.ivanov.test3205team.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.util.Log
import com.oleg.ivanov.test3205team.app.MyApplication
import com.oleg.ivanov.test3205team.domain.data.DownloadLinkAndFile
import com.oleg.ivanov.test3205team.repository.database.DatabaseProvider
import com.oleg.ivanov.test3205team.repository.provider.ProviderDownloader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject


class FileDownloader(
    private val activity: Activity,
    private var onError: () -> Unit?,
    private var onRemove: () -> Unit?,
    private var onProgress: ((progressInPercent: Double, downloadId: Long) -> Unit)?,
) {

    @Inject
    lateinit var databaseProvider: DatabaseProvider

    @Inject
    lateinit var providerDownloader: ProviderDownloader

    private val downloadManager =
        activity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    init {
        MyApplication.appComponent.inject(this)
    }

    @SuppressLint("Range")
    fun download(uri: String, fileName: String, userName: String, repositoryName: String) {

        Log.i("FileDownloader", "test --> download url:$uri  fileName:$fileName")
        val request = DownloadManager.Request(Uri.parse(uri))

        request.setTitle(fileName)
            .setDescription("Description")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        CoroutineScope(Dispatchers.IO).launch {
            val downloadId = downloadManager.enqueue(request)

            databaseProvider.downloadLinkAndFileAppDao().insert(
                DownloadLinkAndFile(
                    Date().time,
                    link = uri,
                    file = fileName,
                    error = false,
                    downloadId = downloadId,
                    userName = userName,
                    repositoryName = repositoryName,
                    comleted = false,
                )
            )

            setOnProgressListener(downloadId, DELAY)
        }
    }

    @SuppressLint("Range")
    private suspend fun setOnProgressListener(downloadId: Long, timeUpdateMilSec: Long) {
        val q = DownloadManager.Query()
        q.setFilterById(downloadId)

        var downloading = true

        while (downloading) {
            val cursor: Cursor = downloadManager.query(q)
            cursor.moveToFirst()
            val bytesDownloaded: Int = cursor.getInt(
                cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR)
            )
            val bytesTotal: Int =
                cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))

            when (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))) {
                DownloadManager.STATUS_SUCCESSFUL -> {
                    CoroutineScope(Dispatchers.IO).launch {

                        val uriString =
                            cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI))

                        Log.i("FileDownloader", "uriString = $uriString")

                        // Обновляем информацию о файле в базе данных
                        databaseProvider.downloadLinkAndFileAppDao().getFileByDownloadId(downloadId)
                            ?.firstOrNull()?.let { downloadLinkAndFile ->
                                downloadLinkAndFile.file = uriString.toString()
                                downloadLinkAndFile.error = false
                                downloadLinkAndFile.comleted = true

                                providerDownloader.sendUrlLoadedCompleted(
                                    downloadLinkAndFile.link ?: ""
                                )

                                databaseProvider.downloadLinkAndFileAppDao()
                                    .update(downloadLinkAndFile)
                            }
                    }
                    downloading = false
                }

                DownloadManager.STATUS_FAILED -> {
                    withContext(Dispatchers.IO) {
                        databaseProvider.downloadLinkAndFileAppDao().getFileByDownloadId(downloadId)
                            ?.firstOrNull()?.let { downloadLinkAndFile ->
                                downloadLinkAndFile.error = true
                                databaseProvider.downloadLinkAndFileAppDao()
                                    .update(downloadLinkAndFile)
                            }
                    }
                    onError.invoke()
                }
            }

            try {
                withContext(Dispatchers.Main) {
                    if (bytesTotal > 0) {
                        val downloadProgress = (bytesDownloaded * 100.0 / bytesTotal)
                        onProgress?.invoke(downloadProgress, downloadId)
                    } else {
                        onProgress?.invoke(0.0, downloadId)
                    }
                }
            } catch (e: java.lang.Exception) {
            }

            delay(timeUpdateMilSec)
        }
    }

    companion object {
        private const val DELAY = 500L
    }
}
package com.oleg.ivanov.test3205team.repository.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface DownloadLinkAndFileAppDao {
    @Query("SELECT * FROM DownloadLinkAndFile order by id desc")
    fun getAll(): List<DownloadLinkAndFile>?

    @Query("SELECT * FROM DownloadLinkAndFile where link=:link order by id desc")
    fun getFileByLink(link: String): List<DownloadLinkAndFile>?

    @Query("SELECT * FROM DownloadLinkAndFile where downloadId=:downloadId order by id desc")
    fun getFileByDownloadId(downloadId: Long): List<DownloadLinkAndFile>?

    @Query("SELECT * FROM DownloadLinkAndFile where userName=:userName AND comleted = 1 order by id desc")
    fun getFileByDownloadUserName(userName: String): List<DownloadLinkAndFile>?

    @Insert
    fun insert(linkAndFile: DownloadLinkAndFile)

    @Update
    fun update(linkAndFile: DownloadLinkAndFile)

    @Delete
    fun delete(linkAndFile: DownloadLinkAndFile)

    @Query("DELETE FROM DownloadLinkAndFile")
    fun deleteAll()
}
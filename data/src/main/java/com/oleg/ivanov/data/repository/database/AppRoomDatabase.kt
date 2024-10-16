package com.oleg.ivanov.data.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oleg.ivanov.data.repository.model.DownloadLinkAndFile
import com.oleg.ivanov.domain.domain.data.DownloadLinkAndFileDomain

@Database(
    entities = [
        DownloadLinkAndFile::class,
    ], exportSchema = false, version = 1
)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract val downloadLinkAndFileAppDao: DownloadLinkAndFileAppDao

    companion object {
        fun getDataBase(context: Context): AppRoomDatabase {
            return Room.databaseBuilder(context, AppRoomDatabase::class.java, "test3205_v1_1")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}

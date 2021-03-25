package com.wipro.news.app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wipro.news.app.model.NewsDataModel

@Database(entities = [NewsDataModel::class], version = 1)
abstract class VideoDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao

    companion object {
        private var INSTANCE: VideoDatabase? = null
        fun getDatabase(context: Context): VideoDatabase? {
            if (INSTANCE == null) {
                synchronized(VideoDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        VideoDatabase::class.java, "itunevideolist.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}
package com.extreme.zebra.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.extreme.zebra.model.NewsDataModel

@Dao
interface VideoDao {
    @Insert
    fun saveHistoryVideo(videoDetails: NewsDataModel)

    @Query("SELECT * FROM NewsListData")
    fun getHistoryVideo(): List<NewsDataModel>
}
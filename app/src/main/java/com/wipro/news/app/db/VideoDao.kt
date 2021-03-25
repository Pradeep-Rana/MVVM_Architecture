package com.wipro.news.app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.wipro.news.app.model.NewsDataModel

@Dao
interface VideoDao {
    @Insert
    fun saveHistoryVideo(videoDetails: NewsDataModel)

    @Query("SELECT * FROM NewsListData")
    fun getHistoryVideo(): List<NewsDataModel>
}
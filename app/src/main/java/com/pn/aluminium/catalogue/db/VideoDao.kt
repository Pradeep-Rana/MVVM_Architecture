package com.pn.aluminium.catalogue.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pn.aluminium.catalogue.model.NewsDataModel

@Dao
interface VideoDao {
    @Insert
    fun saveHistoryVideo(videoDetails: NewsDataModel)

    @Query("SELECT * FROM NewsListData")
    fun getHistoryVideo(): List<NewsDataModel>
}
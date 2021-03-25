package com.extreme.zebra.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NewsListData")
data class NewsDataModel(
//    @PrimaryKey
//    @ColumnInfo(name = "newsId") val newsId: Int? = 0,
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "imageHref") val imageHref: String? = "",
    @ColumnInfo(name = "title") val title: String? = ""
)

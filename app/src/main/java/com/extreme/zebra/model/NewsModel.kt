package com.extreme.zebra.model

data class NewsModel(
    val rows: List<Row>,
    val title: String
) {
    data class Row(
        val description: String,
        val imageHref: String,
        val title: String
    )
}
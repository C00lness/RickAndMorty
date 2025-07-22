package com.example.data.entities.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbPage(
    @PrimaryKey (autoGenerate = true) val id: Int,
    @ColumnInfo(name = "count") val count: Int?,
    @ColumnInfo(name = "pages") val pages: Int?,
    @ColumnInfo(name = "next") val next: String?,
    @ColumnInfo(name = "prev") val prev: String?,
    @ColumnInfo(name = "url") val url: String?
)
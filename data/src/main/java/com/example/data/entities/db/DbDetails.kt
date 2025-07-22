package com.example.data.entities.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbDetails(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "species") val species: String?,
    @ColumnInfo(name = "status") val status: String?,
    @ColumnInfo(name = "gender") val gender: String?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "location") val location: String?,
    @ColumnInfo(name = "url") val url: String?
)
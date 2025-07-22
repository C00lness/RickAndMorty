package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.entities.db.DbDetails

@Dao
interface DetailsDao
{
    @Query("SELECT * FROM DbDetails WHERE url LIKE :url ")
    fun findByUrl(url: String): List<DbDetails>

    @Insert
    fun insert(details: DbDetails)

    @Delete
    fun delete(details: DbDetails)
}

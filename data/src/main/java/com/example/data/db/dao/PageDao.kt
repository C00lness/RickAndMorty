package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.entities.db.DbPage

@Dao
interface PageDao {

    @Query("SELECT * FROM DbPage WHERE url LIKE :url ")
    fun findByUrl(url: String): DbPage

    @Insert
    fun insert(page:DbPage )

    @Delete
    fun delete(page:DbPage)
}
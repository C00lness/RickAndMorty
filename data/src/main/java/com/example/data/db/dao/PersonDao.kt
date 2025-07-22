package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.entities.db.DbPerson

@Dao
interface PersonDao {
    @Query("SELECT * FROM DbPerson")
    fun getAll(): List<DbPerson>

    @Query("SELECT * FROM DbPerson WHERE url LIKE :url ")
    fun findByUrl(url: String): List<DbPerson>

    @Query("SELECT * FROM DbPerson WHERE id LIKE :id ")
    fun findById(id: Int): List<DbPerson>

    @Insert
    fun insertAll(persons: List<DbPerson> )

    @Insert
    fun insert(person: DbPerson )

    @Delete
    fun delete(person: DbPerson)
}
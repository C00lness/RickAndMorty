package com.example.data.repositories

import com.example.data.entities.db.DbDetails
import com.example.data.entities.db.DbPage
import com.example.data.entities.db.DbPerson

interface LocalDataSource {
    suspend fun getPersons(url: String): List<DbPerson>
    suspend fun getDetails(url: String): List<DbDetails>
    suspend fun getDbPage(url: String): DbPage
    suspend fun setPersons(persons: List<DbPerson>)
    suspend fun setDetails(details: DbDetails)
    suspend fun setDbPage(page: DbPage)
}
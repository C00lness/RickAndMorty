package com.example.data.repositories

import com.example.data.db.dao.DetailsDao
import com.example.data.db.dao.PageDao
import com.example.data.db.dao.PersonDao
import com.example.data.entities.db.DbDetails
import com.example.data.entities.db.DbPage
import com.example.data.entities.db.DbPerson

class LocalDataSourceImpl(private val personDao: PersonDao,
                          private val detailsDao: DetailsDao,
                          private val pageDao: PageDao): LocalDataSource {

    override suspend fun getPersons(url: String) = personDao.findByUrl(url)
    override suspend fun getDetails(url: String) = detailsDao.findByUrl(url)
    override suspend fun getDbPage(url: String) = pageDao.findByUrl(url)

    override suspend fun setPersons(persons: List<DbPerson>) = personDao.insertAll(persons)
    override suspend fun setDetails(details: DbDetails)  = detailsDao.insert(details)
    override suspend fun setDbPage(page: DbPage) = pageDao.insert(page)
}
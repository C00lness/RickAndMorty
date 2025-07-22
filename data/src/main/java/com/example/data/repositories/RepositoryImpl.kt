package com.example.data.repositories

import com.example.data.mappers.toDbDetails
import com.example.data.mappers.toDbPage
import com.example.data.mappers.toDbPerson
import com.example.data.mappers.toDetails
import com.example.data.mappers.toPerson
import com.example.domain.entities.Details
import com.example.domain.entities.Page
import com.example.domain.entities.Person
import com.example.domain.repositories.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImpl(private val remoteDataSource: RemoteDataSource,
                     private val localDataSource: LocalDataSource): Repository {

    override fun getPersons(url: String): Flow<List<Person>> = flow {
        val persons: List<Person>
        val localRes = localDataSource.getPersons(url)
        if(localRes.isNotEmpty())
        {
            persons = localRes.map {res -> res.toPerson()}
            val page = localDataSource.getDbPage(url)

            Page.pages = page.pages?:0
            Page.count = page.count?:0
            Page.next = page.next?:""
            Page.prev = page.prev?:""
            emit(persons)
        }
        else
        {
            val remoteRes = remoteDataSource.getPersons(url)
            val remotePersons = remoteRes.results.map { res -> res.toPerson() }

            localDataSource.setPersons(remotePersons.map { res ->res.toDbPerson(url) })

            Page.pages = remoteRes.info?.pages?:0
            Page.count = remoteRes.info?.count?:0
            Page.next = remoteRes.info?.next?:""
            Page.prev = remoteRes.info?.prev?:""

            localDataSource.setDbPage(Page.toDbPage(url))
            emit(remotePersons)
        }
    }

    override fun getDetails(url: String): Flow<Details> = flow {
        val localRes = localDataSource.getDetails(url)
        if(localRes.isNotEmpty())
            emit(localRes[0].toDetails())

        else
        {
            val remoteRes = remoteDataSource.getDetails(url).toDetails()
            localDataSource.setDetails(remoteRes.toDbDetails(url))
            emit(remoteRes)
        }
    }
    override var page: Page = Page
}
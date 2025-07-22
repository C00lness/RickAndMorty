package com.example.data.repositoryImpl

import com.example.data.api.RetrofitService
import com.example.data.db.database.DetailsDataBase
import com.example.data.db.database.PageDataBase
import com.example.data.db.database.PersonDataBase
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

class RepositoryImpl(private val retrofitService: RetrofitService,
                     private val personDb: PersonDataBase,
                     private val detailsDb: DetailsDataBase,
                     private val pageDb: PageDataBase): Repository {

    override fun getPersons(url: String): Flow<List<Person>> = flow {
        val persons: List<Person>

        val localRes = personDb.personDao().findByUrl(url)
        if(localRes.size > 0)
        {
            persons = localRes.map {res -> res.toPerson()}
            val page = pageDb.pageDao().findByUrl(url)

            Page.pages = page.pages?:0
            Page.count = page.count?:0
            Page.next = page.next?:""
            Page.prev = page.prev?:""
            emit(persons)
        }
        else
        {
            val remoteRes = retrofitService.getPersons(url)
            val persons = remoteRes.results.map { res -> res.toPerson() }

            personDb.personDao().insertAll(persons.map { res ->res.toDbPerson(url) })

            Page.pages = remoteRes.info?.pages?:0
            Page.count = remoteRes.info?.count?:0
            Page.next = remoteRes.info?.next?:""
            Page.prev = remoteRes.info?.prev?:""

            pageDb.pageDao().insert(Page.toDbPage(url))
            emit(persons)
        }
    }

    override fun getDetails(url: String): Flow<Details> = flow {
        val localRes = detailsDb.detailsDao().findByUrl(url)
        if(localRes.size > 0)
        {
            emit(localRes[0].toDetails())
        }
        else
        {
            val remoteRes = retrofitService.getDetails(url).toDetails()
            detailsDb.detailsDao().insert(remoteRes.toDbDetails(url))
            emit(remoteRes)
        }
    }
    override var page: Page = Page
}
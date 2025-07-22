package com.example.domain.repositories

import com.example.domain.entities.Details
import com.example.domain.entities.Page
import com.example.domain.entities.Person
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getPersons(url: String): Flow<List<Person>>
    fun getDetails(url: String): Flow<Details>
    var page: Page
}
package com.example.data.repositories

import com.example.data.entities.ApiDetails
import com.example.data.entities.api.details.person.ApiPerson

interface RemoteDataSource {
    suspend fun getPersons(url: String): ApiPerson
    suspend fun getDetails(url: String): ApiDetails
}
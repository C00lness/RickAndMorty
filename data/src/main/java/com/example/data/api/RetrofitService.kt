package com.example.data.api


import com.example.data.entities.ApiDetails
import com.example.data.entities.api.details.person.ApiPerson
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitService {
    @GET()
    suspend fun getPersons(@Url url : String): ApiPerson
    @GET()
    suspend fun getDetails(@Url url : String): ApiDetails
}

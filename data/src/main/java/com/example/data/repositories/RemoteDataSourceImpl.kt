package com.example.data.repositories

import com.example.data.api.RetrofitService

class RemoteDataSourceImpl(private val retrofitService: RetrofitService): RemoteDataSource {
    override suspend fun getPersons(url: String) = retrofitService.getPersons(url)
    override suspend fun getDetails(url: String) = retrofitService.getDetails(url)
}
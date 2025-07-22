package com.example.data.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://rickandmortyapi.com/api/character/"

fun getRetrofitInstance(base_Url: String): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(base_Url)
        .build()
}

fun getRetrofitInterface(retrofit: Retrofit): RetrofitService {
    return retrofit.create(RetrofitService::class.java)
}


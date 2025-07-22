package com.example.rickandmortyworkmate.di.data


import com.example.data.api.BASE_URL
import com.example.data.api.getRetrofitInstance
import com.example.data.api.getRetrofitInterface
import com.example.data.db.database.DetailsDataBase
import com.example.data.db.database.PageDataBase
import com.example.data.db.database.PersonDataBase
import com.example.data.repositories.LocalDataSource
import com.example.data.repositories.LocalDataSourceImpl
import com.example.data.repositories.RemoteDataSource
import com.example.data.repositories.RemoteDataSourceImpl
import com.example.data.repositories.RepositoryImpl
import com.example.domain.repositories.Repository
import org.koin.dsl.module

val dataModule = module {
    single { BASE_URL }
    single { getRetrofitInstance(get()) }
    single { getRetrofitInterface(get()) }

    single { PersonDataBase.getInstance(get()) }
    single { get<PersonDataBase>().personDao() }

    single { DetailsDataBase.getInstance(get()) }
    single { get<DetailsDataBase>().detailsDao() }

    single { PageDataBase.getInstance(get()) }
    single { get<PageDataBase>().pageDao() }

    single<LocalDataSource>
    {
        LocalDataSourceImpl(personDao = get(), detailsDao = get(), pageDao = get())
    }

    single<RemoteDataSource>
    {
        RemoteDataSourceImpl(retrofitService = get())
    }

    single<Repository> {
        RepositoryImpl(remoteDataSource = get(), localDataSource = get())
    }
}
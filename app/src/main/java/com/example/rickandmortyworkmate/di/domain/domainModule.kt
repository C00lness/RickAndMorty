package com.example.rickandmortyworkmate.di.domain

import com.example.domain.usecases.DetailsUseCase
import com.example.domain.usecases.FilterUseCase
import com.example.domain.usecases.NewPageUseCase
import com.example.domain.usecases.PageUseCase
import com.example.domain.usecases.PersonUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<PersonUseCase> {
        PersonUseCase(repository = get())
    }

    factory<DetailsUseCase> {
        DetailsUseCase(repository = get())
    }

    factory<FilterUseCase> {
        FilterUseCase(repository = get())
    }

    factory<PageUseCase> {
        PageUseCase(repository = get())
    }

    factory<NewPageUseCase> {
        NewPageUseCase(repository = get())
    }
}
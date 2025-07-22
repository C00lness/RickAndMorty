package com.example.rickandmortyworkmate.di.app


import com.example.rickandmortyworkmate.presentation.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
     viewModel { ViewModel(personUseCase = get(), detailsUseCase = get(),
          filterUseCase = get(), pageUseCase = get(), newPageUseCase = get()) }
}
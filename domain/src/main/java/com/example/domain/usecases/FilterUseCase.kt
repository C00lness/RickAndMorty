package com.example.domain.usecases

import com.example.domain.repositories.Repository

class FilterUseCase(private val repository: Repository)  {
    fun getPersons(url: String) = repository.getPersons(url)
}

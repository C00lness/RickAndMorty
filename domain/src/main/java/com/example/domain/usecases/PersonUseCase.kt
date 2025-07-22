package com.example.domain.usecases

import com.example.domain.repositories.Repository

class PersonUseCase(private val repository: Repository) {
    fun getPersons(url:String) = repository.getPersons(url)
}
package com.example.domain.usecases

import com.example.domain.repositories.Repository

class DetailsUseCase(private val repository: Repository) {
    fun getDetails(url: String) = repository.getDetails(url)
}
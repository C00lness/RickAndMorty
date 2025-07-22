package com.example.domain.usecases

import com.example.domain.repositories.Repository

class PageUseCase(private val repository: Repository) {
    fun getInfoPage() = repository.page
}
class NewPageUseCase(private val repository: Repository) {
    fun getNewPage(url: String) = repository.getPersons(url)
}



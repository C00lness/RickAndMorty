package com.example.rickandmortyworkmate.state

import com.example.domain.entities.Person

sealed interface PersonsUIState {
    data class Success(val personsSearch: List<Person>) : PersonsUIState
    data class Error(val message: String) : PersonsUIState
    data object Loading : PersonsUIState
}

sealed interface FilterUIState {
    data class Success(val filterSearch: List<Person>) : FilterUIState
    data class Error(val message: String) : FilterUIState
    data object Loading : FilterUIState
}
package com.example.rickandmortyworkmate.state

import com.example.domain.entities.Details
import com.example.domain.entities.Person

sealed interface UIState {
    data class PersonSuccess(val personsSearch: List<Person>) : UIState
    data class FilterSuccess(val filterSearch: List<Person>) : UIState
    data class DetailsSuccess(val details: Details) : UIState
    data class Error(val message: String) : UIState
    data object Loading : UIState
}

//sealed interface FilterUIState {
//    data class Success(val filterSearch: List<Person>) : FilterUIState
//    data class Error(val message: String) : FilterUIState
//    data object Loading : FilterUIState
//}
package com.example.rickandmortyworkmate.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Details
import com.example.domain.usecases.DetailsUseCase
import com.example.domain.usecases.FilterUseCase
import com.example.domain.usecases.NewPageUseCase
import com.example.domain.usecases.PageUseCase
import com.example.domain.usecases.PersonUseCase
import com.example.rickandmortyworkmate.state.FilterUIState
import com.example.rickandmortyworkmate.state.PersonsUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ViewModel(private val personUseCase: PersonUseCase,
                private val detailsUseCase: DetailsUseCase,
                private val filterUseCase: FilterUseCase,
                private val pageUseCase: PageUseCase,
                private val newPageUseCase: NewPageUseCase
) : ViewModel() {

    private val _detailsState = MutableStateFlow<Details?>(null)
    val detailsState: StateFlow<Details?> = _detailsState

    private val _personState = MutableStateFlow<PersonsUIState>(PersonsUIState.Loading)
    val personState: StateFlow<PersonsUIState> = _personState

    private val _filterState = MutableStateFlow<FilterUIState>(FilterUIState.Loading)
    val filterState: StateFlow<FilterUIState> = _filterState

    private val _personNextPage = mutableStateOf("")
    val personNextPage : State<String> = _personNextPage

    private val _personPrevPage = mutableStateOf("")
    val personPrevPage : State<String> = _personPrevPage

    private val _filterNextPage = mutableStateOf("")
    val filterNextPage : State<String> = _filterNextPage

    private val _filterPrevPage = mutableStateOf("")
    val filterPrevPage : State<String> = _filterPrevPage

    fun getPersons(url: String) = viewModelScope.launch {
        if (url == "")
        {
            _personState.value = PersonsUIState.Loading
            personUseCase.getPersons(url)
                .flowOn(Dispatchers.IO)
                .catch { _personState.emit(PersonsUIState.Error(it.message.toString())) }
                .collect { value ->
                        _personState.emit(PersonsUIState.Success(value))
                    _personNextPage.value = pageUseCase.getInfoPage().next
                    _personPrevPage.value = pageUseCase.getInfoPage().prev
                }
        }
        else
        {
            _filterState.value = FilterUIState.Loading
            filterUseCase.getPersons(url)
                .flowOn(Dispatchers.IO)
                .catch {_filterState.emit(FilterUIState.Error(it.message.toString()))}
                .collect { value ->
                        _filterState.emit(FilterUIState.Success(value))
                    _filterNextPage.value = pageUseCase.getInfoPage().next
                    _filterPrevPage.value = pageUseCase.getInfoPage().prev
                }
        }
    }
    init{getPersons("")}

    fun getDetails(url: String) = viewModelScope.launch {
        detailsUseCase.getDetails(url)
            .flowOn(Dispatchers.IO)
            .catch { Log.d("Error", it.message.toString())}
            .collect { value -> _detailsState.emit(value)}
    }

    fun getNewPage(url: String) = viewModelScope.launch {
        if (!((url.contains("name" ) or url.contains("status") or
            url.contains("gender" ) or url.contains("species") or url.contains("type"))))
        {
            _personState.value = PersonsUIState.Loading
            newPageUseCase.getNewPage(url)
                .flowOn(Dispatchers.IO)
                .catch { _personState.emit(PersonsUIState.Error(it.message.toString())) }
                .collect { value ->
                    _personState.emit(PersonsUIState.Success(value))
                    _personNextPage.value = pageUseCase.getInfoPage().next
                    _personPrevPage.value = pageUseCase.getInfoPage().prev
                }
        }
        else
        {
            _filterState.value = FilterUIState.Loading
            newPageUseCase.getNewPage(url)
                .flowOn(Dispatchers.IO)
                .catch {_filterState.emit(FilterUIState.Error(it.message.toString()))}
                .collect { value ->
                    _filterState.emit(FilterUIState.Success(value))
                    _filterNextPage.value = pageUseCase.getInfoPage().next
                    _filterPrevPage.value = pageUseCase.getInfoPage().prev
                }
        }
    }
}
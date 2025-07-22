package com.example.rickandmortyworkmate.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.DetailsUseCase
import com.example.domain.usecases.FilterUseCase
import com.example.domain.usecases.NewPageUseCase
import com.example.domain.usecases.PageUseCase
import com.example.domain.usecases.PersonUseCase
import com.example.rickandmortyworkmate.state.UIState
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

    private val _detailsState = MutableStateFlow<UIState>(UIState.Loading)
    val detailsState: StateFlow<UIState?> = _detailsState

    private val _personState = MutableStateFlow<UIState>(UIState.Loading)
    val personState: StateFlow<UIState> = _personState

    private val _filterState = MutableStateFlow<UIState>(UIState.Loading)
    val filterState: StateFlow<UIState> = _filterState

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
            _personState.value = UIState.Loading
            personUseCase.getPersons(url)
                .flowOn(Dispatchers.IO)
                .catch { _personState.emit(UIState.Error(it.message.toString())) }
                .collect { value ->
                        _personState.emit(UIState.PersonSuccess(value))
                    _personNextPage.value = pageUseCase.getInfoPage().next
                    _personPrevPage.value = pageUseCase.getInfoPage().prev
                }
        }
        else
        {
            _filterState.value = UIState.Loading
            filterUseCase.getPersons(url)
                .flowOn(Dispatchers.IO)
                .catch {_filterState.emit(UIState.Error(it.message.toString()))}
                .collect { value ->
                        _filterState.emit(UIState.FilterSuccess(value))
                    _filterNextPage.value = pageUseCase.getInfoPage().next
                    _filterPrevPage.value = pageUseCase.getInfoPage().prev
                }
        }
    }
    init{getPersons("")}

    fun getDetails(url: String) = viewModelScope.launch {
        _detailsState.value = UIState.Loading
        detailsUseCase.getDetails(url)
            .flowOn(Dispatchers.IO)
            .catch { _detailsState.emit(UIState.Error(it.message.toString()))}
            .collect { value -> _detailsState.emit(UIState.DetailsSuccess(value))}
    }

    fun getNewPage(url: String) = viewModelScope.launch {
        if (!((url.contains("name" ) or url.contains("status") or
            url.contains("gender" ) or url.contains("species") or url.contains("type"))))
        {
            _personState.value = UIState.Loading
            newPageUseCase.getNewPage(url)
                .flowOn(Dispatchers.IO)
                .catch { _personState.emit(UIState.Error(it.message.toString())) }
                .collect { value ->
                    _personState.emit(UIState.PersonSuccess(value))
                    _personNextPage.value = pageUseCase.getInfoPage().next
                    _personPrevPage.value = pageUseCase.getInfoPage().prev
                }
        }
        else
        {
            _filterState.value = UIState.Loading
            newPageUseCase.getNewPage(url)
                .flowOn(Dispatchers.IO)
                .catch {_filterState.emit(UIState.Error(it.message.toString()))}
                .collect { value ->
                    _filterState.emit(UIState.FilterSuccess(value))
                    _filterNextPage.value = pageUseCase.getInfoPage().next
                    _filterPrevPage.value = pageUseCase.getInfoPage().prev
                }
        }
    }
}
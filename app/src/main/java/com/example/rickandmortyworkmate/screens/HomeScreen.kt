package com.example.rickandmortyworkmate.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rickandmortyworkmate.presentation.ViewModel
import com.example.rickandmortyworkmate.state.PersonsUIState

@Composable
fun HomeScreen(
    viewModel: ViewModel,
    onDetailsClicked: (String) -> Unit,
    onFilterClicked: () -> Unit,
    onBackClicked: () -> Unit
) {

    Column(modifier = Modifier.padding(top = 30.dp))
    {
        Filters(viewModel, onFilterClicked, onBackClicked, false)
        Box(modifier = Modifier.padding(10.dp))
        {
            val viewState = viewModel.personState.collectAsStateWithLifecycle()
            when (val state = viewState.value) {
                is PersonsUIState.Loading -> LoadingScreen()
                is PersonsUIState.Success -> PersonsGridScreen(
                    pers = state.personsSearch, onDetailsClicked
                )
                is PersonsUIState.Error -> ErrorScreen(state.message)
                else -> {}
            }
        }
    }
}


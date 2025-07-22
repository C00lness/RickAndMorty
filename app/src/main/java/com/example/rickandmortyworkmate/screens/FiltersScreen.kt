package com.example.rickandmortyworkmate.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rickandmortyworkmate.presentation.ViewModel
import com.example.rickandmortyworkmate.state.FilterUIState
import com.example.rickandmortyworkmate.ui.theme.myBrown
import com.example.rickandmortyworkmate.ui.theme.myGreen

@Composable
fun FilterScreen(
    viewModel: ViewModel,
    onDetailsClicked: (String) -> Unit,
    onFilterClicked: () -> Unit,
    onBackClicked: () -> Unit
) {

    Column(modifier = Modifier.padding(top = 30.dp))
    {
        Filters(viewModel, onFilterClicked, onBackClicked,true)
        Box(modifier = Modifier.padding(10.dp))
        {
            val viewState = viewModel.filterState.collectAsStateWithLifecycle()
            when (val state = viewState.value) {
                is FilterUIState.Loading -> LoadingScreen()
                is FilterUIState.Success -> PersonsGridScreen(
                    pers = state.filterSearch, onDetailsClicked
                )
                is FilterUIState.Error -> ErrorScreen(state.message)
                else -> {}
            }
        }
    }
}

@Composable
fun Filters(viewModel: ViewModel,
            onFilterClicked: () -> Unit,
            onBackClicked: () -> Unit,
            filter:Boolean)

{
    val statusOptions = listOf("alive", "dead", "unknown")
    var selectedStatusOptions by remember { mutableStateOf("") }
    val genderOptions = listOf("female", "male", "genderless", "unknown")
    var selectedgenderOptions by remember { mutableStateOf("") }

    var name by rememberSaveable { mutableStateOf("") }
    var species by rememberSaveable { mutableStateOf("") }
    var type by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.padding(start = 15.dp), horizontalAlignment = Alignment.CenterHorizontally)
    {
        Row()
        {
            TextField(
                value = name,
                onValueChange = {name = it},

                label = { Text("Name", fontSize = 12.sp) },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Name") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = myGreen,
                    unfocusedTextColor = myBrown,
                    focusedContainerColor = Color.White,
                    focusedTextColor = Color.DarkGray,
                ), modifier = Modifier.padding(bottom = 3.dp, end = 3.dp).width(120.dp)
            )
            TextField(
                value = species,
                onValueChange = {species = it},

                label = { Text("Species", fontSize = 12.sp) },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Species") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = myGreen,
                    unfocusedTextColor = myBrown,
                    focusedContainerColor = Color.White,
                    focusedTextColor = Color.DarkGray,
                ), modifier = Modifier.padding(bottom = 3.dp, end = 3.dp).width(120.dp)
            )
            TextField(
                value = type,
                onValueChange = {type = it},

                label = { Text("Type", fontSize = 12.sp) },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Type") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = myGreen,
                    unfocusedTextColor = myBrown,
                    focusedContainerColor = Color.White,
                    focusedTextColor = Color.DarkGray,
                ), modifier = Modifier.padding(bottom = 3.dp, end = 3.dp).width(120.dp)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround)
        {
            genderOptions.forEach { option ->
                RadioButton(
                    selected = (option == selectedgenderOptions),
                    onClick = { selectedgenderOptions = option },
                )
                Text(text = option, fontSize = 10.sp, modifier = Modifier.padding(top = 2.dp))
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            statusOptions.forEach { option ->
                RadioButton(
                    selected = (option == selectedStatusOptions),
                    onClick = { selectedStatusOptions = option }
                )
                Text(text = option, fontSize = 10.sp, modifier = Modifier.padding(top = 2.dp))
            }
        }
        var query = ""

        if (selectedgenderOptions != "") query = query + "gender="+ selectedgenderOptions
        if (selectedStatusOptions != "")
        {
            if (query != "") query = query + "&"
            query = query + "status="+ selectedStatusOptions
        }

        if (name != "")
        {
            if (query != "") query = query + "&"
            query = query + "name="+ name
        }

        if (species != "")
        {
            if (query != "") query = query + "&"
            query = query + "species="+ species
        }

        if (type != "")
        {
            if (query != "") query = query + "&"
            query = query + "type="+ type
        }
        if (query != "") query = "?" + query

        Row(horizontalArrangement = Arrangement.SpaceBetween)
        {
            ExtendedFloatingActionButton(
                icon = { Icon(Icons.Filled.Home, contentDescription = "Back") },
                text = { Text("Back", fontSize = 9.sp, color = myBrown) },
                onClick = { onBackClicked() }, containerColor = myGreen, contentColor = myBrown

            )
            ExtendedFloatingActionButton(
                icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
                text = { Text("Search", fontSize = 9.sp, color = myBrown) },
                onClick = {
                    viewModel.getPersons(query)
                    onFilterClicked()
                }, containerColor = myGreen, contentColor = myBrown
            )
            ExtendedFloatingActionButton(
                icon = { Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Prev") },
                text = { Text("Prev", fontSize = 9.sp, color = myBrown) },
                onClick = {
                    if (filter)
                    {
                        if (viewModel.filterPrevPage.value != "")
                            viewModel.getNewPage(viewModel.filterPrevPage.value)
                    }
                    else
                    {
                        if (viewModel.personPrevPage.value != "")
                            viewModel.getNewPage(viewModel.personPrevPage.value)
                    }

                }, containerColor = myGreen, contentColor = myBrown
            )
            ExtendedFloatingActionButton(
                icon = { Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Next") },
                text = { Text("Next", fontSize = 9.sp, color = myBrown) },
                onClick = {
                    if (filter)
                    {
                        if (viewModel.filterNextPage.value != "")
                            viewModel.getNewPage(viewModel.filterNextPage.value)
                    }
                    else
                    {
                        if (viewModel.personNextPage.value != "")
                            viewModel.getNewPage(viewModel.personNextPage.value)
                    }
                }, containerColor = myGreen, contentColor = myBrown
            )
        }
    }
}
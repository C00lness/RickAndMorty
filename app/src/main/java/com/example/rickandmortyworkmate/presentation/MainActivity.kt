package com.example.rickandmortyworkmate.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortyworkmate.navigation.DetailsDestination
import com.example.rickandmortyworkmate.navigation.FilterPageDestination
import com.example.rickandmortyworkmate.navigation.HomePageDestination
import com.example.rickandmortyworkmate.screens.DetailsScreen
import com.example.rickandmortyworkmate.screens.FilterScreen
import com.example.rickandmortyworkmate.screens.HomeScreen
import com.example.rickandmortyworkmate.ui.theme.RickAndMortyWorkmateTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<ViewModel>()
    private val INDEX = "index"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyWorkmateTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = HomePageDestination.route) {
                    composable(route = HomePageDestination.route) {
                        HomeScreen(viewModel,
                            onDetailsClicked = { entry ->
                                navController.navigate(DetailsDestination.route + entry)},
                            onFilterClicked = { navController.navigateSingleTopTo(FilterPageDestination.route)},
                            onBackClicked = { navController.navigateUp()})
                    }

                    composable(DetailsDestination.route + "/{$INDEX}") { entry ->
                        val index = entry.arguments?.getString(INDEX)
                        viewModel.getDetails(index.toString())
                        DetailsScreen(viewModel)
                    }

                    composable(FilterPageDestination.route) { entry ->
                        FilterScreen (viewModel,
                            onDetailsClicked = { navController.navigateSingleTopTo(DetailsDestination.route + entry)},
                            onFilterClicked = { navController.navigate(FilterPageDestination.route)},
                            onBackClicked = { navController.navigate(HomePageDestination.route)})
                    }
                }
            }
        }
    }

    private fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) {
        launchSingleTop = true
    }
}
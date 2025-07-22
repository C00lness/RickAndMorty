package com.example.rickandmortyworkmate.navigation


interface Route {
    val route: String
}

object HomePageDestination : Route {
    override val route: String = "home_page"
}

object DetailsDestination : Route {
    override val route: String = "details"
}

object FilterPageDestination : Route {
    override val route: String = "filter_page"
}

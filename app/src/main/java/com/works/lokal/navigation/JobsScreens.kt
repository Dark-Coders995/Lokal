package com.works.lokal.navigation

enum class JobsScreens {
    HomeScreen,
    BookMarkScreen,
    DetailScreen;
    companion object{
        fun fromRoute(route: String?):JobsScreens
                = when(route?.substringBefore("/")){
            HomeScreen.name->HomeScreen
            DetailScreen.name ->DetailScreen
            BookMarkScreen.name -> BookMarkScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognised")
        }
    }
}
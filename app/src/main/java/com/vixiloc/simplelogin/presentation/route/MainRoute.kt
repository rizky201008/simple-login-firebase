package com.vixiloc.simplelogin.presentation.route

sealed class MainRoute(val route: String, val name: String) {
    object HomeScreen : MainRoute(route = "/home", "home")
    object LoginScreen : MainRoute(route = "/login", "login")
}

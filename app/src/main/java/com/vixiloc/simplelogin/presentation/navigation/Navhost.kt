package com.vixiloc.simplelogin.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vixiloc.simplelogin.presentation.home.HomeScreen
import com.vixiloc.simplelogin.presentation.login.LoginScreen
import com.vixiloc.simplelogin.presentation.route.MainRoute

@Composable
fun MainNav(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MainRoute.LoginScreen.route) {
        composable(MainRoute.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(MainRoute.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
    }
}
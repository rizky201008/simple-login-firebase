package com.vixiloc.simplelogin.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.vixiloc.simplelogin.presentation.route.MainRoute
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeScreenViewModel = getViewModel()) {
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        if (viewModel.user_fb == null) {
            navController.popBackStack()
            navController.navigate(MainRoute.LoginScreen.route)
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Welcome Bro!")
    }
}
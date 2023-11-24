package com.vixiloc.simplelogin.presentation.home

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.vixiloc.simplelogin.presentation.route.MainRoute
import com.vixiloc.simplelogin.presentation.utils.PerformLifecycle
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeScreenViewModel = getViewModel()) {
    val scope = rememberCoroutineScope()
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    var auth: FirebaseAuth = Firebase.auth
    val activity = (LocalContext.current as? Activity)

    PerformLifecycle(
        lifecycleOwner = lifecycleOwner,
        onStart = {
            scope.launch {
                Log.i("SimLog", "HomeScreen: ${auth.currentUser?.email}")
            }
        },
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Welcome ${auth.currentUser?.email}!", modifier = Modifier.clickable {
            auth.signOut()
            activity?.finish()
        })
    }
}
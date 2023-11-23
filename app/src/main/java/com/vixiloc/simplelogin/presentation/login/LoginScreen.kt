package com.vixiloc.simplelogin.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vixiloc.simplelogin.R
import com.vixiloc.simplelogin.presentation.route.MainRoute
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel = getViewModel(),
    navController: NavHostController,
) {
    val state = viewModel.state
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.loginEvents.collect { event ->
            when (event) {
                is LoginScreenViewModel.LoginEvent.Loading -> {
                    Toast.makeText(context, "Login Loading", Toast.LENGTH_SHORT).show()
                }

                is LoginScreenViewModel.LoginEvent.Success -> {
                    Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                    navController.navigate(MainRoute.HomeScreen.route)
                }

                is LoginScreenViewModel.LoginEvent.Error -> {
                    Toast.makeText(context, "Login Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = state.email,
            onValueChange = {
                viewModel.onEvent(LoginFormEvent.EmailChanged(it))
            },
            textStyle = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                textColor = Color.Black,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            label = {
                Text(
                    text = stringResource(R.string.email_address),
                    style = MaterialTheme.typography.bodySmall
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                autoCorrect = true
            ),
            shape = MaterialTheme.shapes.large,
            isError = state.emailError != null
        )
        if (state.emailError != null) {
            Text(
                text = state.emailError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
        TextField(
            value = state.password,
            onValueChange = {
                viewModel.onEvent(LoginFormEvent.PasswordChanged(it))
            },
            textStyle = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                textColor = Color.Black,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            label = {
                Text(
                    text = stringResource(R.string.password),
                    style = MaterialTheme.typography.bodySmall
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                autoCorrect = true
            ),
            shape = MaterialTheme.shapes.large,
            visualTransformation = PasswordVisualTransformation(),
            isError = state.passwordError != null
        )
        if (state.passwordError != null) {
            Text(
                text = state.passwordError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
        Button(
            onClick = {
                viewModel.onEvent(LoginFormEvent.Submit)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp),
            shape = MaterialTheme.shapes.large
        ) {
            Text(text = stringResource(R.string.login), style = MaterialTheme.typography.bodyMedium)
        }
    }
}
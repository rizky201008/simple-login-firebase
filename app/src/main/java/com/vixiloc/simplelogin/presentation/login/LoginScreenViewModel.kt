package com.vixiloc.simplelogin.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vixiloc.simplelogin.commons.Resource
import com.vixiloc.simplelogin.domain.data.UserCredential
import com.vixiloc.simplelogin.domain.use_case.LoginUseCase
import com.vixiloc.simplelogin.domain.use_case.ValidateEmail
import com.vixiloc.simplelogin.domain.use_case.ValidatePassword
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow

class LoginScreenViewModel(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val login: LoginUseCase
) : ViewModel() {

    var state by mutableStateOf(LoginFormState())

    private val loginEventChannel = Channel<LoginEvent>()
    val loginEvents = loginEventChannel.receiveAsFlow()

    fun onEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }

            is LoginFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is LoginFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
            )
            return
        } else {
            loginFirebase(email = state.email, password = state.password)
            state = state.copy(
                emailError = null,
                passwordError = null,
            )
        }
    }

    private fun loginFirebase(email: String, password: String) {
        val data = UserCredential(email = email, password = password)
        login(data).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    loginEventChannel.send(LoginEvent.Success)
                }

                is Resource.Error -> {
                    loginEventChannel.send(LoginEvent.Error)
                }

                is Resource.Loading -> {
                    loginEventChannel.send(LoginEvent.Error)
                }
            }
        }.launchIn(viewModelScope)
    }

    sealed class LoginEvent {
        object Success : LoginEvent()
        object Error : LoginEvent()
        object Loading : LoginEvent()
    }
}
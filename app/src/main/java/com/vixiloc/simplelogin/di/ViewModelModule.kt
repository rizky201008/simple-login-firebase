package com.vixiloc.simplelogin.di

import com.vixiloc.simplelogin.presentation.home.HomeScreenViewModel
import com.vixiloc.simplelogin.presentation.login.LoginScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel {
        LoginScreenViewModel(login = get(), validateEmail = get(), validatePassword = get())
    }
    viewModel {
        HomeScreenViewModel()
    }
}
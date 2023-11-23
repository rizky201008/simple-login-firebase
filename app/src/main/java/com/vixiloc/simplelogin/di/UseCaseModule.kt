package com.vixiloc.simplelogin.di

import com.vixiloc.simplelogin.domain.use_case.LoginUseCase
import com.vixiloc.simplelogin.domain.use_case.ValidateEmail
import com.vixiloc.simplelogin.domain.use_case.ValidatePassword
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val useCaseModules = module {
    single {
        LoginUseCase(repo = get())
    }
    single {
        ValidatePassword(androidContext())
    }
    single {
        ValidateEmail(androidContext())
    }
}